package info.cellardoor.CliniqueSolis.Patient.Controller;

import info.cellardoor.CliniqueSolis.App.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.App.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<PatientResponse> createPatient(
            @RequestBody PatientRequest patientRequest
    ) {
        return ResponseEntity.ok(patientService.createPatient(patientRequest));
    }

}
