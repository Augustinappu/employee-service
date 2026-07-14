package employee_service.dto;

public class UserProfileUpdateDTO {

    private String userOfficialEmail;
    private String userPersonalEmail;
    private String userName;

    private String department;
    private String designation;
    private String jobLevel;
    private String organizationName;

    private boolean active;
    private Long id;

    public UserProfileUpdateDTO() {
    }

    public String getUserOfficialEmail() {
        return userOfficialEmail;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUserOfficialEmail(String userOfficialEmail) {
        this.userOfficialEmail = userOfficialEmail;
    }

    public String getUserPersonalEmail() {
        return userPersonalEmail;
    }

    public void setUserPersonalEmail(String userPersonalEmail) {
        this.userPersonalEmail = userPersonalEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}