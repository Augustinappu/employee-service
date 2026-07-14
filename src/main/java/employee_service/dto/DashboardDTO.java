package employee_service.dto;

public class DashboardDTO {

    private Long totalEmployees;
    private Long totalAttendanceRecords;
    private Long totalPayrollRecords;
    private Long totalPerformanceRecords;

    private Double totalPayrollAmount;
    private Double averagePerformanceRating;

    public DashboardDTO() {
    }

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
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

    public Double getTotalPayrollAmount() {
        return totalPayrollAmount;
    }

    public void setTotalPayrollAmount(Double totalPayrollAmount) {
        this.totalPayrollAmount = totalPayrollAmount;
    }

    public Double getAveragePerformanceRating() {
        return averagePerformanceRating;
    }

    public void setAveragePerformanceRating(Double averagePerformanceRating) {
        this.averagePerformanceRating = averagePerformanceRating;
    }
}