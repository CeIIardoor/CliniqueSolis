package info.cellardoor.CliniqueSolis.RendezVous.Controller;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Request.RendezVousRequest;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping("/")
    public ResponseEntity<ListRendezVousResponse> getAll() {
        return ResponseEntity.ok(rendezVousService.getAll());
    }

    @GetMapping("/filtrerParDate")
    public ResponseEntity<ListRendezVousResponse> getByDate(
            @RequestParam(value = "annee", required = false) Integer annee,
            @RequestParam(value = "mois", required = false) Integer mois,
            @RequestParam(value = "jour", required = false) Integer jour
    ) {
        if (jour == null){
            if (mois == null){
                if (annee == null)
                    return ResponseEntity.ok(rendezVousService.getAll());
                return ResponseEntity.ok(rendezVousService.getByPartialDate(annee.toString()));
            }
            return ResponseEntity.ok(rendezVousService.getByPartialDate(annee + "-" + String.format("%02d", mois)));
        }
        if (annee == null || mois == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(rendezVousService.getByDate(annee + "-" + String.format("%02d", mois) + "-" + String.format("%02d", jour)));
    }

    @PostMapping("/createRDV")
    public ResponseEntity<RendezVousResponse> createRendezVous(@RequestBody RendezVousRequest rendezVousRequest) {
        RendezVous rendezVous = rendezVousService.createRendezVous(rendezVousRequest);
        return ResponseEntity.created(URI.create("/api/rendezvous/" + rendezVous.getRendezVousId()))
                .body(RendezVousResponse.builder()
                        .rendezVousId(rendezVous.getRendezVousId())
                        .patientId(rendezVous.getPatient().getPatientId())
                        .medecinId(rendezVous.getMedecin().getMedecinId())
                        .date(rendezVous.getDate())
                        .heure(rendezVous.getHeure())
                        .duree(rendezVous.getDuree())
                        .build());
    }
    @PutMapping("updateRDV/{id}")
    public ResponseEntity<RendezVousResponse> updateRendezVous(@PathVariable("id") Integer rendezVousId,
                                                               @RequestBody RendezVousRequest rendezVousRequest) {
        RendezVous rendezVous = rendezVousService.updateRendezVous(rendezVousId, rendezVousRequest);
        if (rendezVous == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RendezVousResponse.builder()
                .rendezVousId(rendezVous.getRendezVousId())
                .patientId(rendezVous.getPatient().getPatientId())
                .medecinId(rendezVous.getMedecin().getMedecinId())
                .date(rendezVous.getDate())
                .heure(rendezVous.getHeure())
                .duree(rendezVous.getDuree())
                .build());
    }
    @DeleteMapping("deleteRDV/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable("id") Integer rendezVousId) {
        rendezVousService.deleteRendezVous(rendezVousId);
        return ResponseEntity.noContent().build();
    }
}