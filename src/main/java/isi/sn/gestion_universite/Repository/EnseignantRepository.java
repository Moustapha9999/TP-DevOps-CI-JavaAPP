package isi.sn.gestion_universite.Repository;

import isi.sn.gestion_universite.Entite.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
}
