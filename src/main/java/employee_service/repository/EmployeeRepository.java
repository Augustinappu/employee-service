package employee_service.repository;

import java.util.List;

import employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee>
    findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
            String firstName,
            String email,
            String department
    );

    @Query("""
            SELECT COUNT(DISTINCT e.department)
            FROM Employee e
            WHERE e.department IS NOT NULL
              AND TRIM(e.department) <> ''
            """)
    Long countDistinctDepartments();

    @Query("""
            SELECT AVG(e.salary)
            FROM Employee e
            WHERE e.salary IS NOT NULL
            """)
    Double averageSalary();
}