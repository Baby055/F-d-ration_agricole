package hei.school.act_agricole.entity;

import java.time.LocalDate;

public class Appartenance {
    private Long id;
    private Long membreId;
    private Long collectiviteId;
    private LocalDate dateDebut;
    private LocalDate dateFin; //nul si il est tjr actif

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMembreId() {
        return membreId;
    }

    public void setMembreId(Long membreId) {
        this.membreId = membreId;
    }

    public Long getCollectiviteId() {
        return collectiviteId;
    }

    public void setCollectiviteId(Long collectiviteId) {
        this.collectiviteId = collectiviteId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
