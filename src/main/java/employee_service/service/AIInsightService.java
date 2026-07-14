package employee_service.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import employee_service.dto.AIInsightDTO;
import employee_service.entity.Attendance;
import employee_service.entity.Employee;
import employee_service.entity.Payroll;
import employee_service.entity.Performance;
import employee_service.repository.AttendanceRepository;
import employee_service.repository.EmployeeRepository;
import employee_service.repository.PayrollRepository;
import employee_service.repository.PerformanceRepository;

@Service
public class AIInsightService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final PayrollRepository payrollRepository;
    private final PerformanceRepository performanceRepository;

    public AIInsightService(
            EmployeeRepository employeeRepository,
            AttendanceRepository attendanceRepository,
            PayrollRepository payrollRepository,
            PerformanceRepository performanceRepository) {

        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.payrollRepository = payrollRepository;
        this.performanceRepository = performanceRepository;
    }

    public AIInsightDTO generateInsights() {

        List<Employee> employees = employeeRepository.findAll();
        List<Attendance> attendanceRecords = attendanceRepository.findAll();
        List<Payroll> payrollRecords = payrollRepository.findAll();
        List<Performance> performanceRecords = performanceRepository.findAll();

        long totalEmployees = employees.size();

        long totalDepartments = employees.stream()
                .map(Employee::getDepartment)
                .filter(this::hasText)
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .count();

        double averageSalary = employees.stream()
                .map(Employee::getSalary)
                .filter(salary -> salary != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        Map<String, Long> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> normalizeValue(
                                employee.getDepartment(),
                                "Unassigned"),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        Map<String, Long> attendanceByStatus = attendanceRecords.stream()
                .collect(Collectors.groupingBy(
                        attendance -> normalizeValue(
                                attendance.getStatus(),
                                "Unknown"),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        Map<String, Long> payrollByStatus = payrollRecords.stream()
                .collect(Collectors.groupingBy(
                        payroll -> normalizeValue(
                                payroll.getPaymentStatus(),
                                "Unknown"),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        Map<String, Long> performanceByStatus = performanceRecords.stream()
                .collect(Collectors.groupingBy(
                        performance -> normalizeValue(
                                performance.getPerformanceStatus(),
                                "Unknown"),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        long presentAttendance = attendanceRecords.stream()
                .filter(record ->
                        record.getStatus() != null
                                && record.getStatus().equalsIgnoreCase("Present"))
                .count();

        double attendanceRate = attendanceRecords.isEmpty()
                ? 0.0
                : presentAttendance * 100.0 / attendanceRecords.size();

        double averagePerformanceRating = performanceRecords.stream()
                .map(Performance::getRating)
                .filter(rating -> rating != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        double totalPayrollAmount = payrollRecords.stream()
                .map(Payroll::getNetSalary)
                .filter(netSalary -> netSalary != null)
                .mapToDouble(Double::doubleValue)
                .sum();

        String workforceInsight =
                "Organization currently has "
                        + totalEmployees
                        + " employees across "
                        + totalDepartments
                        + " departments.";

        String salaryInsight =
                "Average employee salary is ₹"
                        + String.format("%.2f", averageSalary)
                        + ". Total payroll value is ₹"
                        + String.format("%.2f", totalPayrollAmount)
                        + ".";

        String attendanceInsight =
                "Attendance rate is "
                        + String.format("%.1f", attendanceRate)
                        + "% based on "
                        + attendanceRecords.size()
                        + " records.";

        String recommendation = createRecommendation(
                attendanceRate,
                averagePerformanceRating,
                payrollRecords
        );

        AIInsightDTO insightDTO = new AIInsightDTO();

        insightDTO.setTotalEmployees(totalEmployees);
        insightDTO.setTotalDepartments(totalDepartments);
        insightDTO.setAverageSalary(averageSalary);
        insightDTO.setTotalAttendanceRecords((long) attendanceRecords.size());
        insightDTO.setTotalPayrollRecords((long) payrollRecords.size());
        insightDTO.setTotalPerformanceRecords((long) performanceRecords.size());

        insightDTO.setWorkforceInsight(workforceInsight);
        insightDTO.setSalaryInsight(salaryInsight);
        insightDTO.setAttendanceInsight(attendanceInsight);
        insightDTO.setRecommendation(recommendation);

        insightDTO.setEmployeesByDepartment(employeesByDepartment);
        insightDTO.setAttendanceByStatus(attendanceByStatus);
        insightDTO.setPayrollByStatus(payrollByStatus);
        insightDTO.setPerformanceByStatus(performanceByStatus);

        return insightDTO;
    }

    private String createRecommendation(
            double attendanceRate,
            double averageRating,
            List<Payroll> payrollRecords) {

        long pendingPayrolls = payrollRecords.stream()
                .filter(payroll ->
                        payroll.getPaymentStatus() != null
                                && payroll.getPaymentStatus()
                                .equalsIgnoreCase("Pending"))
                .count();

        if (attendanceRate < 75) {
            return "Attendance is below the recommended level. Review absences, late arrivals and employee engagement.";
        }

        if (averageRating > 0 && averageRating < 3) {
            return "Average performance is low. Consider training, clearer goals and regular manager feedback.";
        }

        if (pendingPayrolls > 0) {
            return pendingPayrolls
                    + " payroll record(s) are pending. Complete payroll processing and verify payment status.";
        }

        return "Workforce indicators are stable. Continue monitoring attendance, payroll and employee performance.";
    }

    private String normalizeValue(String value, String defaultValue) {

        if (!hasText(value)) {
            return defaultValue;
        }

        String cleanedValue = value.trim().toLowerCase();

        return Character.toUpperCase(cleanedValue.charAt(0))
                + cleanedValue.substring(1);
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}