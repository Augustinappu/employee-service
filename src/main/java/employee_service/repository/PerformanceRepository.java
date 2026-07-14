package employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee_service.entity.Performance;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

}