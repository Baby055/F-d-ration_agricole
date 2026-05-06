package hei.school.act_agricole.dto.response;

public class CollectivityLocalStatisticsResponse {
    private MemberDescriptionResponse memberDescription;
    private double earnedAmount;
    private double unpaidAmount;

   
    public MemberDescriptionResponse getMemberDescription() { return memberDescription; }
    public void setMemberDescription(MemberDescriptionResponse memberDescription) { this.memberDescription = memberDescription; }
    public double getEarnedAmount() { return earnedAmount; }
    public void setEarnedAmount(double earnedAmount) { this.earnedAmount = earnedAmount; }
    public double getUnpaidAmount() { return unpaidAmount; }
    public void setUnpaidAmount(double unpaidAmount) { this.unpaidAmount = unpaidAmount; }
}
