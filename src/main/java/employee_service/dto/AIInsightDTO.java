package employee_service.dto;

import java.util.Map;

public class AIInsightDTO {

    private Long totalEmployees;
    private Long totalDepartments;
    private Double averageSalary;
    private Long totalAttendanceRecords;
    private Long totalPayrollRecords;
    private Long totalPerformanceRecords;

    private String workforceInsight;
    private String salaryInsight;
    private String attendanceInsight;
    private String recommendation;

    // ===== Chart Data =====

    private Map<String, Long> employeesByDepartment;
    private Map<String, Long> attendanceByStatus;
    private Map<String, Long> payrollByStatus;
    private Map<String, Long> performanceByStatus;

    // ===== Getters & Setters =====

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public Long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(Long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Long getTotalAttendanceRecords() {
        return totalAttendanceRecords;
    }

    public void setTotalAttendanceRecords(Long totalAttendanceRecords) {
        this.totalAttendanceRecords = totalAttendanceRecords;
    }

    public Long getTotalPayrollRecords() {
        return totalPayrollRecords;
    }

    public void setTotalPayrollRecords(Long totalPayrollRecords) {
        this.totalPayrollRecords = totalPayrollRecords;
    }

    public Long getTotalPerformanceRecords() {
        return totalPerformanceRecords;
    }

    public void setTotalPerformanceRecords(Long totalPerformanceRecords) {
        this.totalPerformanceRecords = totalPerformanceRecords;
    }

    public String getWorkforceInsight() {
        return workforceInsight;
    }

    public void setWorkforceInsight(String workforceInsight) {
        this.workforceInsight = workforceInsight;
    }

    public String getSalaryInsight() {
        return salaryInsight;
    }

    public void setSalaryInsight(String salaryInsight) {
        this.salaryInsight = salaryInsight;
    }

    public String getAttendanceInsight() {
        return attendanceInsight;
    }

    public void setAttendanceInsight(String attendanceInsight) {
        this.attendanceInsight = attendanceInsight;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Map<String, Long> getEmployeesByDepartment() {
        return employeesByDepartment;
    }

    public void setEmployeesByDepartment(Map<String, Long> employeesByDepartment) {
        this.employeesByDepartment = employeesByDepartment;
    }

    public Map<String, Long> getAttendanceByStatus() {
        return attendanceByStatus;
    }

    public void setAttendanceByStatus(Map<String, Long> attendanceByStatus) {
        this.attendanceByStatus = attendanceByStatus;
    }

    public Map<String, Long> getPayrollByStatus() {
        return payrollByStatus;
    }

    public void setPayrollByStatus(Map<String, Long> payrollByStatus) {
        this.payrollByStatus = payrollByStatus;
    }

    public Map<String, Long> getPerformanceByStatus() {
        return performanceByStatus;
    }

    public void setPerformanceByStatus(Map<String, Long> performanceByStatus) {
        this.performanceByStatus = performanceByStatus;
    }
}