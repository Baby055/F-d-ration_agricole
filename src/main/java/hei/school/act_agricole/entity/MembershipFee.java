package hei.school.act_agricole.entity;

import hei.school.act_agricole.enums.ActivityStatus;
import hei.school.act_agricole.enums.Frequency;

import java.time.LocalDate;

public class MembershipFee {
    private String id;
    private String collectivityId;
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private double amount;
    private String label;
    private ActivityStatus status;

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
    public LocalDate getEligibleFrom() {
        return eligibleFrom;
    }
    public void setEligibleFrom(LocalDate eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
    }
    public Frequency getFrequency() {
        return frequency;
    }
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public ActivityStatus getStatus() {
        return status;
    }
    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
    
}
