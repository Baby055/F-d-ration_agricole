package hei.school.act_agricole.dto.request;

public class CreateAttendanceRequest {
    private String memberId;
    private boolean present;
    private boolean excused;
    private String reason;

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
