package employee_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee_service.dto.AttendanceDTO;
import employee_service.entity.Attendance;
import employee_service.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceDTO createAttendance(AttendanceDTO dto) {

        Attendance attendance = new Attendance();

        attendance.setEmployeeId(dto.getEmployeeId());
        attendance.setEmployeeName(dto.getEmployeeName());
        attendance.setDate(dto.getDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setWorkingHours(dto.getWorkingHours());
        attendance.setStatus(dto.getStatus());

        attendanceRepository.save(attendance);

        return toDTO(attendance);
    }

    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AttendanceDTO getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return toDTO(attendance);
    }

    public AttendanceDTO updateAttendance(Long id, AttendanceDTO dto) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setEmployeeId(dto.getEmployeeId());
        attendance.setEmployeeName(dto.getEmployeeName());
        attendance.setDate(dto.getDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setWorkingHours(dto.getWorkingHours());
        attendance.setStatus(dto.getStatus());

        attendanceRepository.save(attendance);

        return toDTO(attendance);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    private AttendanceDTO toDTO(Attendance attendance) {

        AttendanceDTO dto = new AttendanceDTO();

        dto.setId(attendance.getId());
        dto.setEmployeeId(attendance.getEmployeeId());
        dto.setEmployeeName(attendance.getEmployeeName());
        dto.setDate(attendance.getDate());
        dto.setCheckIn(attendance.getCheckIn());
        dto.setCheckOut(attendance.getCheckOut());
        dto.setWorkingHours(attendance.getWorkingHours());
        dto.setStatus(attendance.getStatus());

        return dto;
    }
}