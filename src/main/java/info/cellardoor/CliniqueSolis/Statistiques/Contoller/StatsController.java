package info.cellardoor.CliniqueSolis.Statistiques.Contoller;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.Statistiques.Model.Stats;
import info.cellardoor.CliniqueSolis.Statistiques.Service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    final public StatsService statsService;
@Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/GET")
    public List<Stats> getStats() {
        return statsService.getStats() ;
    }
    @PostMapping("/POST")
    public void postStats(@RequestBody Stats statistique){
        System.out.println(statistique);

    }

    @GetMapping("/rendezvous-par-date")
    public Map<String, Long> getNombreRendezVousParDate() {
        return statsService.calculerNombreRendezVousParDate();
    }
    @GetMapping("/patients-par-age")
    public Map<Integer, Long> getNombrePatientsParAge() {
        return statsService.calculerNombrePatientsParAge();
    }
    @GetMapping("/medecins-par-specialite")
    public Map<String, Long> getNombreMedecinsParSpecialite() {
        return statsService.calculerNombreMedecinsParSpecialite();
    }
    @GetMapping("/consultations-par-date")
    public Map<String, Long> getNombreConsultationsParDate() {
        return statsService.calculerNombreConsultationsParDate();
    }



}
