
package isi.sn.gestion_universite.Entite;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enseignant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer matr;

  private String nom;
  private String prenom;
  private String grade;

  @ManyToOne
  @JoinColumn(name = "codedep")
  @JsonIgnore
  private Departement departement;


  public Integer getMatr() {
    return matr; }
  public String getNom() {
    return nom; }
  public void setNom(String nom) {
    this.nom = nom; }
  public String getPrenom() {
    return prenom; }
  public void setPrenom(String prenom) {
    this.prenom = prenom; }
  public String getGrade() {
    return grade; }
  public void setGrade(String grade) {
    this.grade = grade; }
  public Departement getDepartement() {
    return departement; }
  public void setDepartement(Departement departement) {
    this.departement = departement; }
}
