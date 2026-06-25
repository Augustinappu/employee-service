package employee_service.controller;

import employee_service.entity.Employee;
import employee_service.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import employee_service.dto.EmployeeRequestDTO;
import employee_service.dto.EmployeeResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
        return service.saveEmployee(dto);
    }
    @GetMapping
    public List<Employee> getEmployees() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return service.delete(id);
    }
}