package hei.school.act_agricole.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cotisation {
    private Long id;
    private Long collectiviteId;
    private Long membreId;
    private BigDecimal montant;
    private TypeCotisation type;
    private LocalDate dateEncaissement;
    private ModePaiement mode;
    private String referencePaiement;

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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public TypeCotisation getType() {
        return type;
    }

    public void setType(TypeCotisation type) {
        this.type = type;
    }

    public ModePaiement getMode() {
        return mode;
    }

    public void setMode(ModePaiement mode) {
        this.mode = mode;
    }

    public LocalDate getDateEncaissement() {
        return dateEncaissement;
    }

    public void setDateEncaissement(LocalDate dateEncaissement) {
        this.dateEncaissement = dateEncaissement;
    }

    public String getReferencePaiement() {
        return referencePaiement;
    }

    public void setReferencePaiement(String referencePaiement) {
        this.referencePaiement = referencePaiement;
    }
}
