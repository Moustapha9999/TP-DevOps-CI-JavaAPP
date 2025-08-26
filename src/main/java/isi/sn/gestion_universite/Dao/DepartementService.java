package isi.sn.gestion_universite.Dao;

import isi.sn.gestion_universite.Entite.Departement;
import isi.sn.gestion_universite.Repository.DepartementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {
  private final DepartementRepository departementRepository;

  public DepartementService(DepartementRepository departementRepository) {
    this.departementRepository = departementRepository;
  }

  public List<Departement> findAll() {
    return departementRepository.findAll();
  }

  public Departement findById(String codedep) {
    return departementRepository.findById(codedep).orElse(null);
  }

  public void save(Departement departement) {
    departementRepository.save(departement);
  }

  public void deleteById(String codedep) {
    departementRepository.deleteById(codedep);
  }
}
