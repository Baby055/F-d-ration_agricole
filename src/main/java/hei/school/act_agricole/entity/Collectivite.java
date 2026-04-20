package hei.school.act_agricole.entity;

import java.time.LocalDate;

public class Collectivite {
    private long id;
    private String nom;
    private String numeroUnique;
    private String specialite;
    private String ville;
    private LocalDate dateCreation;
    private String autorisationFederation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroUnique() {
        return numeroUnique;
    }

    public void setNumeroUnique(String numeroUnique) {
        this.numeroUnique = numeroUnique;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAutorisationFederation() {
        return autorisationFederation;
    }

    public void setAutorisationFederation(String autorisationFederation) {
        this.autorisationFederation = autorisationFederation;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
