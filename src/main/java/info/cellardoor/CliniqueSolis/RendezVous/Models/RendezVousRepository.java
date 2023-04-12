package info.cellardoor.CliniqueSolis.RendezVous.Models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous ,Long> {

    List<RendezVous> findByDate(LocalDate date);
    Page<RendezVous> findByDate(LocalDate date, Pageable pageable);
    List<RendezVous> findByDateAndHeure(LocalDate date, LocalDate heure);
    List<RendezVous> findByDateAndHeureAndNom(LocalDate date, LocalDate heure, String nom);
    List<RendezVous> findByDateAndHeureAndNomAndPrenom(LocalDate date, LocalDate heure, String nom, String prenom);
    List<RendezVous> findByDateAndHeureAndNomAndPrenomAndCin(LocalDate date, LocalDate heure, String nom, String prenom, String cin);

}
