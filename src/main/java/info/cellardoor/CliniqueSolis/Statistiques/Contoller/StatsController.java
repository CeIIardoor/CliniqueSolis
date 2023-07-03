package info.cellardoor.CliniqueSolis.Statistiques.Contoller;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.Statistiques.Model.Stats;
import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.ListMedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Statistiques.Http.Response.StatsPatients;
import info.cellardoor.CliniqueSolis.Statistiques.Service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/nombre/medecin")
    public ResponseEntity<StatsPatients> getMedecinscount()
    {
        return ResponseEntity.ok(statsService.getNombreMedcin());
    }
    @PostMapping("/create")
    public ResponseEntity<MedecinResponse> createMedecin(
            @RequestBody MedecinRequest medecinRequest
    ) {
        return ResponseEntity.ok(statsService.createMedecin(medecinRequest));
    }
    @GetMapping("/nombrepatient")
    public ResponseEntity<ListMedecinResponse> getAll() {
        return ResponseEntity.ok(statsService.getAll());
    }
    @GetMapping("/search")
    public ResponseEntity<ListMedecinResponse> getByCinStartingWith(
            @RequestParam(value = "cin", required = false) String cin) {
        if (cin == null) {
            return ResponseEntity.ok(statsService.getAll());
        }
        return ResponseEntity.ok(statsService.findByCinStartingWith(cin));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedecinById(

    @GetMapping("/GET")
    public List<Stats> getStats() {
        return statsService.getStats() ;
    }
    @PostMapping("/POST")
    public void postStats(@RequestBody Stats statistique){
        System.out.println(statistique);

    }

//    @GetMapping("/rendezvous-par-date")
//    public Map<String, Long> getNombreRendezVousParDate() {
//        return statsService.calculerNombreRendezVousParDate();
//    }
    @GetMapping("/rendezvous-par-date")
    public ResponseEntity<Map<String, Long>> getNombreRendezVousParDate() {
        Map<String, Long> nombreRendezVousParDate = statsService.calculerNombreRendezVousParDate();
        return ResponseEntity.ok(nombreRendezVousParDate);
    }

//    @GetMapping("/patients-par-age")
//    public Map<Integer, Long> getNombrePatientsParAge() {
//        return statsService.calculerNombrePatientsParAge();
//    }
    @GetMapping("/patients-par-age")
    public ResponseEntity<Map<Integer, Long>> getNombrePatientsParAge() {
        Map<Integer, Long> nombrePatientsParAge = statsService.calculerNombrePatientsParAge();
        return ResponseEntity.ok(nombrePatientsParAge);
    }
//    @GetMapping("/medecins-par-specialite")
//    public Map<String, Long> getNombreMedecinsParSpecialite() {
//        return statsService.calculerNombreMedecinsParSpecialite();
//    }
    @GetMapping("/medecins-par-specialite")
    public ResponseEntity<Map<String, Long>> getNombreMedecinsParSpecialite() {
        Map<String, Long> nombreMedecinsParSpecialite = statsService.calculerNombreMedecinsParSpecialite();
        return ResponseEntity.ok(nombreMedecinsParSpecialite);
    }
//    @GetMapping("/consultations-par-date")
//    public Map<String, Long> getNombreConsultationsParDate() {
//        return statsService.calculerNombreConsultationsParDate();
//    }
@GetMapping("/consultations-par-date")
    public ResponseEntity<Map<String, Long>> getNombreConsultationsParDate() {
        Map<String, Long> nombreConsultationsParDate = statsService.calculerNombreConsultationsParDate();
        return ResponseEntity.ok(nombreConsultationsParDate);
    }



}
