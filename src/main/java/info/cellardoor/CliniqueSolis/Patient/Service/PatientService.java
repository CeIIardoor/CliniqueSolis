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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

    public ListPatientResponse findByCinStartingWith(String cin) {
        List<Patient> patients = patientRepository.findByCinStartingWith(cin);
        if (patients.size() == 0)
            return null;
        return ListPatientResponse.builder()
                .patients(patients.stream().map(patient -> PatientResponse.builder()
                                .patientId(patient.getPatientId())
                                .cin(patient.getCin())
                                .groupeSanguin(patient.getGroupeSanguin())
                                .email(patient.getUser().getEmail())
                                .nom(patient.getUser().getNom())
                                .prenom(patient.getUser().getPrenom())
                                .role(patient.getUser().getRole().toString())
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


    public PatientResponse getPatientResponse(PatientRequest patientRequest, Patient patient) {
        BeanUtils.copyProperties(patientRequest, patient, getNullPropertyNames(patientRequest));
        var savedPatient = patientRepository.save(patient);
        var user = patient.getUser();
        BeanUtils.copyProperties(patientRequest, user, getNullPropertyNames(patientRequest));
        var savedUser = userRepository.save(user);
        return PatientResponse.builder()
                .patientId(savedPatient.getPatientId())
                .cin(savedPatient.getCin())
                .groupeSanguin(savedPatient.getGroupeSanguin())
                .nom(savedUser.getNom())
                .prenom(savedUser.getPrenom())
                .role(String.valueOf(savedUser.getRole()))
                .email(String.valueOf(savedUser.getEmail()))
                .build();
    }
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }




    public ListPatientResponse getAll() {
        var patients = patientRepository.findAll();
        if (patients.size() == 0)
            return null;
        return ListPatientResponse.builder()
                .patients(patients.stream().map(patient -> PatientResponse.builder()
                                .patientId(patient.getPatientId())
                                .cin(patient.getCin())
                                .groupeSanguin(patient.getGroupeSanguin())
                                .nom(patient.getUser().getNom())
                                .prenom(patient.getUser().getPrenom())
                                .email(patient.getUser().getEmail())
                                .role(patient.getUser().getRole().toString())
                                .build())
                        .toList())
                .build();
    }

}