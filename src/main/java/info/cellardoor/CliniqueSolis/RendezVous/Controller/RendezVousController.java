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

    @GetMapping("/all")
    public ResponseEntity<ListRendezVousResponse> getAll() {
        return ResponseEntity.ok(rendezVousService.getAll());
    }

    @GetMapping("/date/{year}/{month}/{day}")
    public ResponseEntity<ListRendezVousResponse> getByDate(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day
    ) {
        if (year != null)
            if (month != null)
                if (day != null)
                    return ResponseEntity.ok(rendezVousService.getByDate(year, month, day));
                else
                    return ResponseEntity.ok(rendezVousService.getByMonth(year,month));
            else
                return ResponseEntity.ok(rendezVousService.getByYear(year));

        return ResponseEntity.ok(rendezVousService.getAll());
    }
}