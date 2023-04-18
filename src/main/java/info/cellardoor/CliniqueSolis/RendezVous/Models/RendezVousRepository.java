package info.cellardoor.CliniqueSolis.RendezVous.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous ,Integer> {
    List<RendezVous> findByDate(String date);
    List<RendezVous> findByDateStartingWith(String date);

}