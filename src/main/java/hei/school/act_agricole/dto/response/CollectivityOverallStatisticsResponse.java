package hei.school.act_agricole.dto.response;

public class CollectivityOverallStatisticsResponse {
    private CollectivityInformationResponse collectivityInformation; // à définir ou utiliser un DTO existant
    private int newMembersNumber;
    private double overallMemberCurrentDuePercentage;

    public CollectivityInformationResponse getCollectivityInformation() {
        return collectivityInformation;
    }

    public void setCollectivityInformation(CollectivityInformationResponse collectivityInformation) {
        this.collectivityInformation = collectivityInformation;
    }

    public int getNewMembersNumber() {
        return newMembersNumber;
    }

    public void setNewMembersNumber(int newMembersNumber) {
        this.newMembersNumber = newMembersNumber;
    }

    public double getOverallMemberCurrentDuePercentage() {
        return overallMemberCurrentDuePercentage;
    }

    public void setOverallMemberCurrentDuePercentage(double overallMemberCurrentDuePercentage) {
        this.overallMemberCurrentDuePercentage = overallMemberCurrentDuePercentage;
    }
}
