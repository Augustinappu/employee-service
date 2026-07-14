package employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee_service.entity.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

}