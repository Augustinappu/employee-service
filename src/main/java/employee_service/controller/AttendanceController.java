package employee_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee_service.dto.AttendanceDTO;
import employee_service.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    public ResponseEntity<AttendanceDTO> createAttendance(
            @RequestBody AttendanceDTO attendanceDTO) {

        return ResponseEntity.ok(
                attendanceService.createAttendance(attendanceDTO)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceDTO attendanceDTO) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(id, attendanceDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok("Attendance deleted successfully");
    }
}