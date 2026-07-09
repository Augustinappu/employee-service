package employee_service.repository;

import employee_service.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByFirstNameContainingIgnoreCase(String keyword);
	@Query("SELECT COUNT(DISTINCT e.department) FROM Employee e")
	Long countDistinctDepartments();
	@Query("SELECT AVG(e.salary) FROM Employee e")
	Double averageSalary();
}