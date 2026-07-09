package employee_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee_service.repository.EmployeeRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        return employeeRepository.averageSalary();
    }
}