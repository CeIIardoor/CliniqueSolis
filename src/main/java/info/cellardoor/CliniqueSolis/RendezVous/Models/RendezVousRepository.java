package info.cellardoor.CliniqueSolis.RendezVous.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous ,Integer> {

    List<RendezVous> findByDate(Date date);

    List<RendezVous> findByDateBetween(Date date1, Date date2);


}