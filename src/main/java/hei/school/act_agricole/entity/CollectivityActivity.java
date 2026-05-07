package hei.school.act_agricole.entity;

import java.time.LocalDate;

public class CollectivityActivity {
    private String id;
    private String collectivityId;
    private String title;
    private String description;
    private LocalDate activityDate;
    private boolean mandatory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

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
