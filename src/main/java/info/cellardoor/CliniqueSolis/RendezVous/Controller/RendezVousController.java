package info.cellardoor.CliniqueSolis.RendezVous.Controller;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping("/all")
    public ResponseEntity<ListRendezVousResponse> getAll() {
        return ResponseEntity.ok(rendezVousService.getAll());
    }

    @GetMapping("/search/{year}/{month}/{day}")
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
    }
}