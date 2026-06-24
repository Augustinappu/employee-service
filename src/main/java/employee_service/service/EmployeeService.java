package employee_service.service;

import employee_service.entity.Employee;
import employee_service.exception.EmployeeNotFoundException;
import employee_service.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import employee_service.dto.EmployeeRequestDTO;
import employee_service.dto.EmployeeResponseDTO;

import java.util.List;

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

        EmployeeResponseDTO response = new EmployeeResponseDTO();

        response.setId(savedEmployee.getId());
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setEmail(savedEmployee.getEmail());
        response.setDepartment(savedEmployee.getDepartment());
        response.setSalary(savedEmployee.getSalary());

        return response;
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

        public Employee update(Long id, Employee newEmployee) {
        	Employee employee = repository.findById(id)
        	        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

          
                employee.setFirstName(newEmployee.getFirstName());
                employee.setLastName(newEmployee.getLastName());
                employee.setEmail(newEmployee.getEmail());
                employee.setDepartment(newEmployee.getDepartment());
                employee.setSalary(newEmployee.getSalary());
                return repository.save(employee);
            }


        public String delete(Long id) {
            repository.deleteById(id);
            return "Employee deleted successfully";
        }
        
    
}