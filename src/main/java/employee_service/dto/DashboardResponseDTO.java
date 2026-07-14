package employee_service.dto;

public class DashboardResponseDTO {

    private Long totalEmployees;
    private Long totalDepartments;
    private Double averageSalary;
    private Long totalAttendanceRecords;
    private Long totalPayrollRecords;
    private Long totalPerformanceRecords;
    private Double totalPayrollAmount;
    private Double averagePerformanceRating;

    public DashboardResponseDTO(
            Long totalEmployees,
            Long totalDepartments,
            Double averageSalary,
            Long totalAttendanceRecords,
            Long totalPayrollRecords,
            Long totalPerformanceRecords,
            Double totalPayrollAmount,
            Double averagePerformanceRating) {

        this.totalEmployees = totalEmployees;
        this.totalDepartments = totalDepartments;
        this.averageSalary = averageSalary;
        this.totalAttendanceRecords = totalAttendanceRecords;
        this.totalPayrollRecords = totalPayrollRecords;
        this.totalPerformanceRecords = totalPerformanceRecords;
        this.totalPayrollAmount = totalPayrollAmount;
        this.averagePerformanceRating = averagePerformanceRating;
    }

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public Long getTotalDepartments() {
        return totalDepartments;
    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public Long getTotalAttendanceRecords() {
        return totalAttendanceRecords;
    }

    public Long getTotalPayrollRecords() {
        return totalPayrollRecords;
    }

    public Long getTotalPerformanceRecords() {
        return totalPerformanceRecords;
    }

    public Double getTotalPayrollAmount() {
        return totalPayrollAmount;
    }

    public Double getAveragePerformanceRating() {
        return averagePerformanceRating;
    }
}