package info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories;

import info.cellardoor.CliniqueSolis.Comptabilite.Controllers.DevisController;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Revenus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenusRepository extends JpaRepository<Revenus, Integer> {
}
