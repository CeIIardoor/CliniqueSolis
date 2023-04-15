package info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories;

import info.cellardoor.CliniqueSolis.Comptabilite.Controllers.DevisController;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Revenus;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Tresorerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TresorerieRepository extends JpaRepository<Tresorerie, Integer> {
}
