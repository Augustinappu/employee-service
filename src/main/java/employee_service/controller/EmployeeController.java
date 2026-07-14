package employee_service.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee_service.dto.EmployeeRequestDTO;
import employee_service.dto.EmployeeResponseDTO;
import employee_service.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeResponseDTO addEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return service.saveEmployee(dto);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getEmployees() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping("/search")
    public List<EmployeeResponseDTO> searchEmployees(
            @RequestParam String keyword) {

        return service.searchEmployees(keyword);
    }

    @GetMapping("/paged")
    public Page<EmployeeResponseDTO> getEmployeesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getEmployeesWithPagination(page, size);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        return service.delete(id);
    }
}