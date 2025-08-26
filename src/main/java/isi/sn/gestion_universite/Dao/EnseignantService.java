package isi.sn.gestion_universite.Dao;

import isi.sn.gestion_universite.Entite.Enseignant;
import isi.sn.gestion_universite.Repository.EnseignantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {
  private final EnseignantRepository enseignantRepository;

  public EnseignantService(EnseignantRepository enseignantRepository) {
    this.enseignantRepository = enseignantRepository;
  }

  public List<Enseignant> findAll() {
    return enseignantRepository.findAll();
  }

  public Enseignant findById(Integer matr) {
    return enseignantRepository.findById(matr).orElse(null);
  }

  public void save(Enseignant enseignant) {
    enseignantRepository.save(enseignant);
  }

  public void deleteById(Integer matr) {
    enseignantRepository.deleteById(matr);
  }
}
