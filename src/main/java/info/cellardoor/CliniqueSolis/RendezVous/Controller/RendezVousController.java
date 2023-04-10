package info.cellardoor.CliniqueSolis.RendezVous.Controller;

import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rendezvous")
public class RendezVousController {
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @GetMapping
    public List<RendezVous> getRendezVous(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date != null) {
            return rendezVousRepository.findByDate(date);
        } else {
            return rendezVousRepository.findAll();
        }
    }

    @PostMapping
    public RendezVous ajouterRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }
}
