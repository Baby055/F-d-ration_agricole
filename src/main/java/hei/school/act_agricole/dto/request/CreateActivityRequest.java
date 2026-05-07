package hei.school.act_agricole.dto.request;

import java.time.LocalDate;

public class CreateActivityRequest {
    private String title;
    private String description;
    private LocalDate activityDate;
    private boolean mandatory;

    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getActivityDate() {
        return activityDate;
    }
    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }
    public boolean isMandatory() {
        return mandatory;
    }
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    
}
