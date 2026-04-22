package hei.school.act_agricole.dto.response;

import java.time.LocalDate;

import hei.school.act_agricole.entity.FinancialAccount;
import hei.school.act_agricole.enums.PaymentMode;

public class MemberPayementResponse {
    private String id;
    private double amount;
    private PaymentMode paymentMode;
    private FinancialAccount accountCredited;
    private LocalDate creationDate;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public FinancialAccount getAccountCredited() {
        return accountCredited;
    }
    public void setAccountCredited(FinancialAccount accountCredited) {
        this.accountCredited = accountCredited;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
