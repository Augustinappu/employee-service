package employee_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee_service.dto.PerformanceDTO;
import employee_service.service.PerformanceService;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "*")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/create")
    public ResponseEntity<PerformanceDTO> createPerformance(
            @RequestBody PerformanceDTO performanceDTO) {

        return ResponseEntity.ok(
                performanceService.createPerformance(performanceDTO)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<PerformanceDTO>> getAllPerformances() {

        return ResponseEntity.ok(
                performanceService.getAllPerformances()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDTO> getPerformanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                performanceService.getPerformanceById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PerformanceDTO> updatePerformance(
            @PathVariable Long id,
            @RequestBody PerformanceDTO performanceDTO) {

        return ResponseEntity.ok(
                performanceService.updatePerformance(id, performanceDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePerformance(
            @PathVariable Long id) {

        performanceService.deletePerformance(id);

        return ResponseEntity.ok(
                "Performance record deleted successfully"
        );
    }
}