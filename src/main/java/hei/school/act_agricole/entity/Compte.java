package hei.school.act_agricole.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compte {
    private Long id;
    private String entiteType; //"COLLECTIVITY" ou "FEDERATION"
    private Long entiteId; // id de la collectivite ou null pour federation
    private TypeCompte typeCompte;
    private String titulaire;
    private BigDecimal solde;
    private LocalDate dateSolde;
    private String banque;
    private String numeroCompte;
    private String serviceMobile; //ORANGE MONEY , MVOLE , AIRTEL
    private String numeroTelephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntiteType() {
        return entiteType;
    }

    public void setEntiteType(String entiteType) {
        this.entiteType = entiteType;
    }

    public Long getEntiteId() {
        return entiteId;
    }

    public void setEntiteId(Long entiteId) {
        this.entiteId = entiteId;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public LocalDate getDateSolde() {
        return dateSolde;
    }

    public void setDateSolde(LocalDate dateSolde) {
        this.dateSolde = dateSolde;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getServiceMobile() {
        return serviceMobile;
    }

    public void setServiceMobile(String serviceMobile) {
        this.serviceMobile = serviceMobile;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}
