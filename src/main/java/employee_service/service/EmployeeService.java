package employee_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import employee_service.dto.EmployeeRequestDTO;
import employee_service.dto.EmployeeResponseDTO;
import employee_service.entity.Employee;
import employee_service.exception.EmployeeNotFoundException;
import employee_service.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        Employee savedEmployee = repository.save(employee);

        return convertToDTO(savedEmployee);
    }

    public List<EmployeeResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public EmployeeResponseDTO getById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id));

        return convertToDTO(employee);
    }

    public List<EmployeeResponseDTO> searchEmployees(String keyword) {

        return repository
                .findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                )
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public Page<EmployeeResponseDTO> getEmployeesWithPagination(
            int page,
            int size) {

        return repository.findAll(PageRequest.of(page, size))
                .map(this::convertToDTO);
    }

    public EmployeeResponseDTO update(
            Long id,
            EmployeeRequestDTO dto) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        Employee updatedEmployee = repository.save(employee);

        return convertToDTO(updatedEmployee);
    }

    public String delete(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id));

        repository.delete(employee);

        return "Employee deleted successfully";
    }

    private EmployeeResponseDTO convertToDTO(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());

        return dto;
    }
}