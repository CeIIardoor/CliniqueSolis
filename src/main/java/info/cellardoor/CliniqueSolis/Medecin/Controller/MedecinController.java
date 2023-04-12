package info.cellardoor.CliniqueSolis.Medecin.Controller;

import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medecin")
@RequiredArgsConstructor
public class MedecinController {
    private final MedecinService medecinService;
    @GetMapping("/{id}")
    public ResponseEntity<MedecinResponse> getMedecinById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(medecinService.getMedecinById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<MedecinResponse> createMedecin(
            @RequestBody MedecinRequest medecinRequest
    ) {
        return ResponseEntity.ok(medecinService.createMedecin(medecinRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedecinById(

            @PathVariable("id") Integer id
    ) {
        medecinService.deleteMedecinById(id);
        return ResponseEntity.ok("Medecin supprimé");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MedecinResponse> updateMedecinById(
            @PathVariable("id") Integer id,
            @RequestBody MedecinRequest medecinRequest
    ) {
        return ResponseEntity.ok(medecinService.updateMedecinById(id, medecinRequest));
    }

}