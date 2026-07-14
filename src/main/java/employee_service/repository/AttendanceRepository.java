package employee_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee_service.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStatus(String status);

    List<Attendance> findByEmployeeId(Long employeeId);

    List<Attendance> findByDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}