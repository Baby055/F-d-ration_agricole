package hei.school.act_agricole.entity;

import java.time.LocalDate;

public class MemberPayment {
    private String id;
    private String memberId;
    private String membershipFeeId;
    private double amount;
    private PaymentMode paymentMode;
    private String transactionId;
    private LocalDate creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMembershipFeeId() {
        return membershipFeeId;
    }

    public void setMembershipFeeId(String membershipFeeId) {
        this.membershipFeeId = membershipFeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
