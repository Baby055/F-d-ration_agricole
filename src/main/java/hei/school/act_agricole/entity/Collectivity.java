package hei.school.act_agricole.entity;

public class Collectivity {
    private String id;
    private String location;
    private boolean federationApproval;

    public String getId() {  
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }       
    public void setLocation(String location) {
        this.location = location;
    }
    public boolean isFederationApproval() {
        return federationApproval;
    }
    public void setFederationApproval(boolean federationApproval) {
        this.federationApproval = federationApproval;
    }
}
