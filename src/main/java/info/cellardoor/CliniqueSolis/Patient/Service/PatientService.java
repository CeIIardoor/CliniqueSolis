package info.cellardoor.CliniqueSolis.Patient.Service;

import info.cellardoor.CliniqueSolis.App.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.App.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.Element;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientResponse getPatientById(Integer id) {
        var patient = patientRepository.findByPatientId(id).orElseThrow(() -> new NoSuchElementException("Patient not found"));
        return PatientResponse.builder()
                .patientId(patient.getPatientId())
                .cin(patient.getCin())
                .groupeSanguin(patient.getGroupeSanguin())
                .build();
    }
    public PatientResponse createPatient(PatientRequest patientRequest) {
        var patient = new Patient(
                patientRequest.getNom(),
                patientRequest.getPrenom(),
                patientRequest.getCin(),
                patientRequest.getGroupeSanguin()
        );
        var savedUser =userRepository.save(patient.getUser());
        var savedPatient = patientRepository.save(patient);
        return PatientResponse.builder()
                .patientId(savedPatient.getPatientId())
                .cin(savedPatient.getCin())
                .groupeSanguin(savedPatient.getGroupeSanguin())
                .userId(savedUser.getUserId())
                .nom(savedUser.getNom())
                .prenom(savedUser.getPrenom())
                .build();
    }
}
