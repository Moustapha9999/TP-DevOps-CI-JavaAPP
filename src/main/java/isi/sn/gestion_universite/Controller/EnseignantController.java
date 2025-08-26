package isi.sn.gestion_universite.Controller;

import isi.sn.gestion_universite.Entite.Enseignant;
import isi.sn.gestion_universite.Entite.Departement;
import isi.sn.gestion_universite.Dao.DepartementService;
import isi.sn.gestion_universite.Dao.EnseignantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/enseignants")
public class EnseignantController {
  private final EnseignantService enseignantService;
  private final DepartementService departementService;

  public EnseignantController(EnseignantService enseignantService,
                              DepartementService departementService) {
    this.enseignantService = enseignantService;
    this.departementService = departementService;
  }

  // Liste des enseignants
  @GetMapping
  public String listEnseignants(Model model) {
    try {
      model.addAttribute("enseignants", enseignantService.findAll());
      return "enseignants/list";
    } catch (Exception e) {
      model.addAttribute("error", "Erreur de chargement: " + e.getMessage());
      return "enseignants/list";
    }
  }

  // Afficher le formulaire de création
  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("enseignant", new Enseignant());
    model.addAttribute("departements", departementService.findAll());
    return "enseignants/create";
  }

  // Sauvegarder un nouvel enseignant
  @PostMapping("/save")
  public String saveEnseignant(@ModelAttribute Enseignant enseignant,
                               @RequestParam("departementCode") String departementCode,
                               RedirectAttributes redirectAttributes) {
    try {
      if (departementCode != null && !departementCode.isEmpty()) {
        Departement dep = departementService.findById(departementCode);
        if (dep == null) {
          throw new IllegalArgumentException("Département invalide");
        }
        enseignant.setDepartement(dep);
      }
      enseignantService.save(enseignant);
      redirectAttributes.addFlashAttribute("success", "Enseignant ajouté avec succès");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout: " + e.getMessage());
    }
    return "redirect:/enseignants";
  }

  // Afficher le formulaire d'édition
  @GetMapping("/edit/{matr}")
  public String showEditForm(@PathVariable Integer matr, Model model) {
    Enseignant enseignant = enseignantService.findById(matr);
    if (enseignant != null) {
      model.addAttribute("enseignant", enseignant);
      model.addAttribute("departements", departementService.findAll());
      return "enseignants/edit";
    }
    return "redirect:/enseignants";
  }

  // Mettre à jour un enseignant
  @PostMapping("/update")
  public String updateEnseignant(@ModelAttribute Enseignant enseignant,
                                 @RequestParam("departementCode") String departementCode,
                                 RedirectAttributes redirectAttributes) {
    try {
      if (departementCode != null && !departementCode.isEmpty()) {
        Departement dep = departementService.findById(departementCode);
        if (dep == null) {
          throw new IllegalArgumentException("Département invalide");
        }
        enseignant.setDepartement(dep);
      }
      enseignantService.save(enseignant);
      redirectAttributes.addFlashAttribute("success", "Enseignant modifié avec succès");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification: " + e.getMessage());
    }
    return "redirect:/enseignants";
  }

  // Supprimer un enseignant
  @GetMapping("/delete/{matr}")
  public String deleteEnseignant(@PathVariable Integer matr,
                                 RedirectAttributes redirectAttributes) {
    try {
      enseignantService.deleteById(matr);
      redirectAttributes.addFlashAttribute("success", "Enseignant supprimé avec succès");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression: " + e.getMessage());
    }
    return "redirect:/enseignants";
  }
}
