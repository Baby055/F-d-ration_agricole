package hei.school.act_agricole.entity;

import java.time.LocalDateTime;

public class Activite {
    private Long id;
    private String organisateurType; // "COLLECTIVITE" ou "FEDERATION"
    private Long organisateurId;     // id collectivité ou null
    private String titre;
    private String description;
    private LocalDateTime dateHeure;
    private boolean obligatoire;
    private String membresConcernesJson; // stocker un JSON des rôles ou IDs (ou une table séparée)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisateurType() {
        return organisateurType;
    }

    public void setOrganisateurType(String organisateurType) {
        this.organisateurType = organisateurType;
    }

    public Long getOrganisateurId() {
        return organisateurId;
    }

    public void setOrganisateurId(Long organisateurId) {
        this.organisateurId = organisateurId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public String getMembresConcernesJson() {
        return membresConcernesJson;
    }

    public void setMembresConcernesJson(String membresConcernesJson) {
        this.membresConcernesJson = membresConcernesJson;
    }
}
