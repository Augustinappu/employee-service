package employee_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee_service.dto.PayrollDTO;
import employee_service.service.PayrollService;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin(origins = "*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/create")
    public ResponseEntity<PayrollDTO> createPayroll(
            @RequestBody PayrollDTO payrollDTO) {

        return ResponseEntity.ok(
                payrollService.createPayroll(payrollDTO)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<PayrollDTO>> getAllPayrolls() {

        return ResponseEntity.ok(
                payrollService.getAllPayrolls()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollDTO> getPayrollById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                payrollService.getPayrollById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PayrollDTO> updatePayroll(
            @PathVariable Long id,
            @RequestBody PayrollDTO payrollDTO) {

        return ResponseEntity.ok(
                payrollService.updatePayroll(id, payrollDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayroll(
            @PathVariable Long id) {

        payrollService.deletePayroll(id);

        return ResponseEntity.ok("Payroll deleted successfully");
    }
}