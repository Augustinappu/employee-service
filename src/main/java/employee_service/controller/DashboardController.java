package employee_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee_service.dto.DashboardResponseDTO;
import employee_service.entity.Payroll;
import employee_service.entity.Performance;
import employee_service.repository.AttendanceRepository;
import employee_service.repository.EmployeeRepository;
import employee_service.repository.PayrollRepository;
import employee_service.repository.PerformanceRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @GetMapping("/total-employees")
    public Long getTotalEmployees() {
        return employeeRepository.count();
    }

    @GetMapping("/total-departments")
    public Long getTotalDepartments() {
        return employeeRepository.countDistinctDepartments();
    }

    @GetMapping("/average-salary")
    public Double getAverageSalary() {

        Double averageSalary = employeeRepository.averageSalary();

        return averageSalary != null ? averageSalary : 0.0;
    }

    @GetMapping("/summary")
    public DashboardResponseDTO getDashboardSummary() {

        Long totalEmployees = employeeRepository.count();

        Long totalDepartments =
                employeeRepository.countDistinctDepartments();

        Double averageSalary =
                employeeRepository.averageSalary();

        if (averageSalary == null) {
            averageSalary = 0.0;
        }

        Long totalAttendanceRecords =
                attendanceRepository.count();

        Long totalPayrollRecords =
                payrollRepository.count();

        Long totalPerformanceRecords =
                performanceRepository.count();

        Double totalPayrollAmount = payrollRepository.findAll()
                .stream()
                .filter(payroll -> payroll.getNetSalary() != null)
                .mapToDouble(Payroll::getNetSalary)
                .sum();

        Double averagePerformanceRating =
                performanceRepository.findAll()
                        .stream()
                        .filter(performance ->
                                performance.getRating() != null)
                        .mapToDouble(Performance::getRating)
                        .average()
                        .orElse(0.0);

        return new DashboardResponseDTO(
                totalEmployees,
                totalDepartments,
                averageSalary,
                totalAttendanceRecords,
                totalPayrollRecords,
                totalPerformanceRecords,
                totalPayrollAmount,
                averagePerformanceRating
        );
    }
}