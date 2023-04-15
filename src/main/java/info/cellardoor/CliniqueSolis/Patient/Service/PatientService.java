package info.cellardoor.CliniqueSolis.Patient.Service;

import info.cellardoor.CliniqueSolis.Patient.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.ListPatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientResponse getPatientById(Integer id) {
        var patient = patientRepository.findByPatientId(id).orElseThrow(() -> new NoSuchElementException("Patient not found"));
        return PatientResponse.builder()
                .patientId(patient.getPatientId())
                .cin(patient.getCin())
                .groupeSanguin(patient.getGroupeSanguin())
                .build();
    }

    public ListPatientResponse getByCinContaining(String cin) {
        List<Patient> patients = patientRepository.findByCinContaining(cin);
        if (patients.size() == 0)
            return null;
        return ListPatientResponse.builder()
                .patients(patients.stream().map(patient -> PatientResponse.builder()
                                .patientId(patient.getPatientId())
                                .cin(patient.getCin())
                                .groupeSanguin(patient.getGroupeSanguin())
                                .build())
                        .toList())
                .build();
    }

    public PatientResponse createPatient(PatientRequest patientRequest) {
        var associatedUser = User.builder()
                .nom(patientRequest.getNom())
                .prenom(patientRequest.getPrenom())
                .email(patientRequest.getEmail())
                .mdp(passwordEncoder.encode(patientRequest.getMdp()))
                .role(Roles.ROLE_PATIENT)
                .build();
        var patient = Patient.builder()
                .cin(patientRequest.getCin())
                .groupeSanguin(patientRequest.getGroupeSanguin())
                .user(associatedUser)
                .build();
        var savedPatient = patientRepository.save(patient);
        return PatientResponse.builder()
                .patientId(savedPatient.getPatientId())
                .cin(savedPatient.getCin())
                .groupeSanguin(savedPatient.getGroupeSanguin())
                .userId(savedPatient.getUser().getUserId())
                .nom(savedPatient.getUser().getNom())
                .prenom(savedPatient.getUser().getPrenom())
                .email(savedPatient.getUser().getEmail())
                .role(savedPatient.getUser().getRole().toString())
                .build();
    }

    public void deletePatientById(Integer id) {
        var patient = patientRepository.findByPatientId(id)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        patientRepository.delete(patient);
    }


    public PatientResponse updatePatientById(Integer id, PatientRequest patientRequest) {
        var patient = patientRepository.findByPatientId(id)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        return getPatientResponse(patientRequest, patient);
    }

    private PatientResponse getPatientResponse(PatientRequest patientRequest, Patient patient) {
        if (patientRequest.getCin() != null) {
            patient.setCin(patientRequest.getCin());
        }
        if (patientRequest.getGroupeSanguin() != null) {
            patient.setGroupeSanguin(patientRequest.getGroupeSanguin());
        }
        var savedPatient = patientRepository.save(patient);
        var user = patient.getUser();
        if (patientRequest.getEmail() != null) {
            patient.getUser().setEmail(patientRequest.getEmail());
        }
        if (patientRequest.getRole() != null) {
            patient.getUser().setRole(Roles.valueOf(patientRequest.getRole()));
        }
        if (patientRequest.getNom() != null) {
            patient.getUser().setNom(patientRequest.getNom());
        }
        if (patientRequest.getPrenom() != null) {
            patient.getUser().setPrenom(patientRequest.getPrenom());
        }
        var savedUser = userRepository.save(user);
        return PatientResponse.builder()
                .patientId(savedPatient.getPatientId())
                .cin(savedPatient.getCin())
                .groupeSanguin(savedPatient.getGroupeSanguin())
                .userId(savedUser.getUserId())
                .nom(savedUser.getNom())
                .prenom(savedUser.getPrenom())
                .role(String.valueOf(savedUser.getRole()))
                .email(String.valueOf(savedUser.getEmail()))
                .build();
    }

    public ListPatientResponse getAllPatients() {
        var patients = patientRepository.findAll();
        return ListPatientResponse.builder()
                .patients(patients.stream().map(patient -> PatientResponse.builder()
                                .patientId(patient.getPatientId())
                                .cin(patient.getCin())
                                .groupeSanguin(patient.getGroupeSanguin())
                                .build())
                        .toList())
                .build();
    }
}