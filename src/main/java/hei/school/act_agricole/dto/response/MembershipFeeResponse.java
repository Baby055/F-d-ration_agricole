package hei.school.act_agricole.dto.response;

import java.time.LocalDate;

import hei.school.act_agricole.enums.ActivityStatus;
import hei.school.act_agricole.enums.Frequency;

public class MembershipFeeResponse {
    private String id;
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private double amount;
    private String label;
    private ActivityStatus status;

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getEligibleFrom() {
        return eligibleFrom;
    }
    public void setEligibleFrom(LocalDate eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
    }

    public  Frequency getFrequency() {
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

