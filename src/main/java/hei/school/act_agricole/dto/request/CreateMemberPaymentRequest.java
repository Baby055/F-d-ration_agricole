package hei.school.act_agricole.dto.request;

import hei.school.act_agricole.enums.PaymentMode;

public class CreateMemberPaymentRequest {
    private double amount;
    private String membershipFeeIdentifier;
    private String accountCreditedIdentifier;
    private PaymentMode paymentMode;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMembershipFeeIdentifier() {
        return membershipFeeIdentifier;
    }

    public void setMembershipFeeIdentifier(String membershipFeeIdentifier) {
        this.membershipFeeIdentifier = membershipFeeIdentifier;
    }

    public String getAccountCreditedIdentifier() {
        return accountCreditedIdentifier;
    }

    public void setAccountCreditedIdentifier(String accountCreditedIdentifier) {
        this.accountCreditedIdentifier = accountCreditedIdentifier;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
