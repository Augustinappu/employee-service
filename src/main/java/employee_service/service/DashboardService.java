package employee_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee_service.dto.DashboardDTO;
import employee_service.entity.Payroll;
import employee_service.entity.Performance;
import employee_service.repository.EmployeeRepository;
import employee_service.repository.AttendanceRepository;
import employee_service.repository.PayrollRepository;
import employee_service.repository.PerformanceRepository;

@Service
public class DashboardService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    public DashboardDTO getDashboardData() {

        DashboardDTO dto = new DashboardDTO();

        dto.setTotalEmployees(employeeRepository.count());
        dto.setTotalAttendanceRecords(attendanceRepository.count());
        dto.setTotalPayrollRecords(payrollRepository.count());
        dto.setTotalPerformanceRecords(performanceRepository.count());

        double totalPayrollAmount = payrollRepository.findAll()
                .stream()
                .mapToDouble(Payroll::getNetSalary)
                .sum();

        dto.setTotalPayrollAmount(totalPayrollAmount);

        double averageRating = performanceRepository.findAll()
                .stream()
                .mapToDouble(Performance::getRating)
                .average()
                .orElse(0.0);

        dto.setAveragePerformanceRating(averageRating);

        return dto;
    }
}