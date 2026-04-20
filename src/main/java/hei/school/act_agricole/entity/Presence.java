package hei.school.act_agricole.entity;

public class Presence {
    private Long id;
    private Long activiteId;
    private Long membreId;
    private boolean present;
    private boolean excuse;
    private String motifAbsence;
    private boolean estInvite; //membre d'une autre collectivite

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public Long getMembreId() {
        return membreId;
    }

    public void setMembreId(Long membreId) {
        this.membreId = membreId;
    }

    public boolean isExcuse() {
        return excuse;
    }

    public void setExcuse(boolean excuse) {
        this.excuse = excuse;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isEstInvite() {
        return estInvite;
    }

    public void setEstInvite(boolean estInvite) {
        this.estInvite = estInvite;
    }

    public String getMotifAbsence() {
        return motifAbsence;
    }

    public void setMotifAbsence(String motifAbsence) {
        this.motifAbsence = motifAbsence;
    }
}
