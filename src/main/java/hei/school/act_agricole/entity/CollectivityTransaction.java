package hei.school.act_agricole.entity;

import hei.school.act_agricole.enums.PaymentMode;

import java.time.LocalDate;

public class CollectivityTransaction {
    private String id;
    private String collectivityId;
    private LocalDate creationDate;
    private double amount;
    private PaymentMode paymentMode;
    private String accountCreditedId;
    private String memberDebitedId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountCreditedId() {
        return accountCreditedId;
    }

    public void setAccountCreditedId(String accountCreditedId) {
        this.accountCreditedId = accountCreditedId;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getMemberDebitedId() {
        return memberDebitedId;
    }

    public void setMemberDebitedId(String memberDebitedId) {
        this.memberDebitedId = memberDebitedId;
    }
}
