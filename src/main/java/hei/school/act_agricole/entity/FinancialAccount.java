package hei.school.act_agricole.entity;

public class FinancialAccount {
    private String id;
    private String accountType; // CASH, MOBILE_BANKING, BANK_ACCOUNT
    private String holderName;
    private double amount;
    private String mobileService;
    private String mobileNumber;
    private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String ribKey;
    private String collectivityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMobileService() {
        return mobileService;
    }

    public void setMobileService(String mobileService) {
        this.mobileService = mobileService;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getRibKey() {
        return ribKey;
    }

    public void setRibKey(String ribKey) {
        this.ribKey = ribKey;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }
}
