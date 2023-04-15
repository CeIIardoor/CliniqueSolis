package info.cellardoor.CliniqueSolis.RendezVous.Controller;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping("/all")
    public ResponseEntity<ListRendezVousResponse> getAll() {
        return ResponseEntity.ok(rendezVousService.getAll());
    }

    @GetMapping("/date")
    public ResponseEntity<RendezVousResponse> getByDate(
            @RequestParam(value = "annee") Integer annee,
            @RequestParam(value = "mois") Integer mois,
            @RequestParam(value = "jour") Integer jour
    ) {
        return ResponseEntity.ok(
                rendezVousService.getByDate(annee+"-"+String.format("%02d", mois)+"-"+String.format("%02d", jour))
        );
    }
}