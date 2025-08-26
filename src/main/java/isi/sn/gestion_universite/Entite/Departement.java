package isi.sn.gestion_universite.Entite;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Departement {
  @Id
  private String codedep;
  private String nomdep;

  @OneToMany(mappedBy = "departement")
  @JsonIgnore // Pour éviter les références circulaires
  private List<Enseignant> enseignants;

  // Getters et Setters
  public String getCodedep() {
    return codedep;
  }

  public void setCodedep(String codedep) {
    this.codedep = codedep;
  }

  public String getNomdep() {
    return nomdep;
  }

  public void setNomdep(String nomdep) {
    this.nomdep = nomdep;
  }

  public List<Enseignant> getEnseignants() {
    return enseignants;
  }

  public void setEnseignants(List<Enseignant> enseignants) {
    this.enseignants = enseignants;
  }
}
