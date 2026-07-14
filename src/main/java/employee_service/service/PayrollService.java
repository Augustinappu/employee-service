package employee_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import employee_service.dto.PayrollDTO;
import employee_service.entity.Payroll;
import employee_service.repository.PayrollRepository;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public PayrollDTO createPayroll(PayrollDTO dto) {

        Payroll payroll = new Payroll();

        updatePayrollFields(payroll, dto);

        Payroll savedPayroll = payrollRepository.save(payroll);

        return toDTO(savedPayroll);
    }

    public List<PayrollDTO> getAllPayrolls() {

        return payrollRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PayrollDTO getPayrollById(Long id) {

        Payroll payroll = findPayrollById(id);

        return toDTO(payroll);
    }

    public PayrollDTO updatePayroll(Long id, PayrollDTO dto) {

        Payroll payroll = findPayrollById(id);

        updatePayrollFields(payroll, dto);

        Payroll updatedPayroll = payrollRepository.save(payroll);

        return toDTO(updatedPayroll);
    }

    public void deletePayroll(Long id) {

        Payroll payroll = findPayrollById(id);

        payrollRepository.delete(payroll);
    }

    private Payroll findPayrollById(Long id) {

        return payrollRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payroll not found with id: " + id
                        )
                );
    }

    private void updatePayrollFields(
            Payroll payroll,
            PayrollDTO dto) {

        payroll.setEmployeeId(dto.getEmployeeId());
        payroll.setEmployeeName(dto.getEmployeeName());

        double basicSalary = valueOrZero(dto.getBasicSalary());
        double allowances = valueOrZero(dto.getAllowances());
        double deductions = valueOrZero(dto.getDeductions());

        payroll.setBasicSalary(basicSalary);
        payroll.setAllowances(allowances);
        payroll.setDeductions(deductions);

        double netSalary =
                basicSalary + allowances - deductions;

        payroll.setNetSalary(netSalary);

        payroll.setPayMonth(dto.getPayMonth());
        payroll.setPaymentStatus(dto.getPaymentStatus());
    }

    private double valueOrZero(Double value) {
        return value != null ? value : 0.0;
    }

    private PayrollDTO toDTO(Payroll payroll) {

        PayrollDTO dto = new PayrollDTO();

        dto.setId(payroll.getId());
        dto.setEmployeeId(payroll.getEmployeeId());
        dto.setEmployeeName(payroll.getEmployeeName());
        dto.setBasicSalary(payroll.getBasicSalary());
        dto.setAllowances(payroll.getAllowances());
        dto.setDeductions(payroll.getDeductions());
        dto.setNetSalary(payroll.getNetSalary());
        dto.setPayMonth(payroll.getPayMonth());
        dto.setPaymentStatus(payroll.getPaymentStatus());

        return dto;
    }
}