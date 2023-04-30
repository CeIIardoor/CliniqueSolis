package info.cellardoor.CliniqueSolis;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Patient.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.Patient.Service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static info.cellardoor.CliniqueSolis.Auth.Models.User.Roles.ROLE_PATIENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testGetPatientResponse() {
        // create mock data
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setCin("GA218629");
        patientRequest.setEmail("elmarouanelouardi@gmail.com");
        patientRequest.setNom("El Ouardi");
        patientRequest.setPrenom("Marouan");
        patientRequest.setRole("ROLE_PATIENT");

        Patient patient = new Patient();
        patient.setPatientId(1);
        patient.setCin("GA218629");

        User user = new User();
        user.setEmail("elmarouanelouardi@gmail.com");
        user.setRole(ROLE_PATIENT);
        user.setNom("Marouan");
        user.setPrenom("Cj");

        patient.setUser(user);

        // mock repository methods
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // invoke the method being tested
        PatientResponse patientResponse = patientService.getPatientResponse(patientRequest, patient);
        patientRepository.save(patient);
        userRepository.save(user);

        // assert the result
        System.out.println(patient.getPatientId() + " | " + patientResponse.getPatientId());
        System.out.println(patientRequest.getCin() + " | " + patientResponse.getCin());
//        System.out.println(patientRequest.getGroupeSanguin() + " | " + patientResponse.getGroupeSanguin());
        System.out.println(patientRequest.getNom() + " | " + patientResponse.getNom());
        System.out.println(patientRequest.getPrenom() + " | " + patientResponse.getPrenom());
        System.out.println(patientRequest.getEmail() + " | " + patientResponse.getEmail());

        assertEquals(patient.getPatientId(), patientResponse.getPatientId());
        assertEquals(patientRequest.getCin(), patientResponse.getCin());
//        assertEquals(patientRequest.getGroupeSanguin(), patientResponse.getGroupeSanguin());
        assertEquals(patientRequest.getNom(), patientResponse.getNom());
        assertEquals(patientRequest.getPrenom(), patientResponse.getPrenom());
        assertEquals(patientRequest.getEmail(), patientResponse.getEmail());
    }
}