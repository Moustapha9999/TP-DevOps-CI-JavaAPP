package isi.sn.gestion_universite.Controller;

import isi.sn.gestion_universite.Entite.Departement;
import isi.sn.gestion_universite.Dao.DepartementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departements")
public class DepartementController {
  private final DepartementService departementService;

  public DepartementController(DepartementService departementService) {
    this.departementService = departementService;
  }

  @GetMapping
  public String listDepartements(Model model) {
    model.addAttribute("departements", departementService.findAll());
    return "departements/list";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("departement", new Departement());
    return "departements/create";
  }

  @PostMapping("/save")
  public String saveDepartement(@ModelAttribute Departement departement,
                                RedirectAttributes redirectAttributes) {
    try {
      departementService.save(departement);
      redirectAttributes.addFlashAttribute("success", "Département ajouté avec succès");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout: " + e.getMessage());
    }
    return "redirect:/departements";
  }

  @GetMapping("/edit/{codedep}")
  public String showEditForm(@PathVariable String codedep, Model model) {
    Departement departement = departementService.findById(codedep);
    if (departement != null) {
      model.addAttribute("departement", departement);
      return "departements/edit";
    }
    return "redirect:/departements";
  }

  @PostMapping("/update")
  public String updateDepartement(@ModelAttribute Departement departement,
                                  RedirectAttributes redirectAttributes) {
    try {
      departementService.save(departement);
      redirectAttributes.addFlashAttribute("success", "Département modifié avec succès");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification: " + e.getMessage());
    }
    return "redirect:/departements";
  }

  @GetMapping("/delete/{codedep}")
  public String deleteDepartement(@PathVariable String codedep,
                                  RedirectAttributes redirectAttributes) {
    try {
      departementService.deleteById(codedep);
      redirectAttributes.addFlashAttribute("success", "Département supprimé avec succès");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression: " + e.getMessage());
    }
    return "redirect:/departements";
  }
}
