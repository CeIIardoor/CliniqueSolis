package info.cellardoor.CliniqueSolis.Comptabilite.Controllers;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FactureRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.FactureService;
import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Facture")
@RequiredArgsConstructor

public class FactureController {
    private final FactureService factureService;
    @GetMapping("/{id}")
    public ResponseEntity<FactureResponse> getMedecinById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(factureService.getFactureById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<FactureResponse> createFacture(
            @RequestBody FactureRequest factureRequest
    ) {
        return ResponseEntity.ok(factureService.createFacture(factureRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedecinById(

            @PathVariable("id") Integer id
    ) {
        factureService.deleteFactureById(id);
        return ResponseEntity.ok("Medecin id:" + id + " supprimé");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<FactureResponse> updateMedecinById(
            @PathVariable("id") Integer id,
            @RequestBody FactureRequest factureRequest
    ) {
        return ResponseEntity.ok(factureService.updateFactureById(id, factureRequest));
    }



}
