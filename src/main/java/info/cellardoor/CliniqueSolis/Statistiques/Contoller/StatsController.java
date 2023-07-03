package info.cellardoor.CliniqueSolis.Statistiques.Contoller;

import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.ListMedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Statistiques.Http.Response.StatsPatients;
import info.cellardoor.CliniqueSolis.Statistiques.Service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            @PathVariable("id") Integer id
    ) {
        statsService.deleteMedecinById(id);
        return ResponseEntity.ok("Medecin id:" + id + " supprimé");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MedecinResponse> updateMedecinById(
            @PathVariable("id") Integer id,
            @RequestBody MedecinRequest medecinRequest
    ) {
        return ResponseEntity.ok(statsService.updateMedecinById(id, medecinRequest));
    }




}
