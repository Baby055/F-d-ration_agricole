package hei.school.act_agricole.dto.response;

public class AttendanceResponse {
    private String memberId;
    private String firstName;
    private String lastName;
    private boolean present;
    private boolean excused;
    private String reason;

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public boolean isPresent() {
        return present;
    }
    public void setPresent(boolean present) {
        this.present = present;
    }
    public boolean isExcused() {
        return excused;
    }
    public void setExcused(boolean excused) {
        this.excused = excused;
    }
    public String getReason() {
        return reason;
    }
}
