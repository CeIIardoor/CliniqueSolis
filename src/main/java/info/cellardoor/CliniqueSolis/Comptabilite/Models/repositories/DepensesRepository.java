package info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories;

import info.cellardoor.CliniqueSolis.Comptabilite.Controllers.DevisController;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Depences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepensesRepository extends JpaRepository<Depences, Integer> {

}
