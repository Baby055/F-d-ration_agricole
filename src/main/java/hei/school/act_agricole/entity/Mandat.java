package hei.school.act_agricole.entity;

import java.time.LocalDate;

public class Mandat {
    private Long id;
    private Long membreId;
    private Long collectiviteId; // null si mandat à la fédération
    private Poste poste;
    private LocalDate debutMandat;
    private LocalDate finMandat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectiviteId() {
        return collectiviteId;
    }

    public void setCollectiviteId(Long collectiviteId) {
        this.collectiviteId = collectiviteId;
    }

    public Long getMembreId() {
        return membreId;
    }

    public void setMembreId(Long membreId) {
        this.membreId = membreId;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public LocalDate getDebutMandat() {
        return debutMandat;
    }

    public void setDebutMandat(LocalDate debutMandat) {
        this.debutMandat = debutMandat;
    }

    public LocalDate getFinMandat() {
        return finMandat;
    }

    public void setFinMandat(LocalDate finMandat) {
        this.finMandat = finMandat;
    }
}
