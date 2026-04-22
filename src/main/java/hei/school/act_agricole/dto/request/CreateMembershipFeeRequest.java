package hei.school.act_agricole.dto.request;

import java.time.LocalDate;

import hei.school.act_agricole.enums.Frequency;

public class CreateMembershipFeeRequest {
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private double amount;
    private String label;

    // Getters and setters
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
}
