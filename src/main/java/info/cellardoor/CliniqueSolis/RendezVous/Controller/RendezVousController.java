package info.cellardoor.CliniqueSolis.RendezVous.Controller;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}