package isi.sn.gestion_universite.Repository;

import isi.sn.gestion_universite.Entite.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, String> {
}
