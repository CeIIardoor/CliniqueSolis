package info.cellardoor.CliniqueSolis.RendezVous.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RendezVousRepository extends JpaRepository<RendezVous ,Integer> {
    Optional<RendezVous> findByDate(String date);

}