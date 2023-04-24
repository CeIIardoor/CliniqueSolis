package info.cellardoor.CliniqueSolis;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Medecin.Service.MedecinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static info.cellardoor.CliniqueSolis.Auth.Models.User.Roles.ROLE_MEDECIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MedecinServiceTest {

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MedecinService medecinService;

    @Test
    public void testGetMedecinResponse() {
        // create mock data
        MedecinRequest medecinRequest = new MedecinRequest();
        medecinRequest.setCin("GA218629");
        medecinRequest.setEmail("elmarouanelouardi@gmail.com");
        medecinRequest.setNom("El Ouardi");
        medecinRequest.setPrenom("Marouan");
        medecinRequest.setRole("ROLE_MEDECIN");

        Medecin medecin = new Medecin();
        medecin.setMedecinId(1);
        medecin.setCin("GA218629");
        User user = new User();
        user.setEmail("elmarouanelouardi@gmail.com");
        user.setRole(ROLE_MEDECIN);
        user.setNom("Marouan");
        user.setPrenom("Cj");

        medecin.setUser(user);

        // mock repository methods
        Mockito.when(medecinRepository.save(Mockito.any(Medecin.class))).thenReturn(medecin);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // invoke the method being tested
        MedecinResponse medecinResponse = medecinService.getMedecinResponse(medecinRequest, medecin);
        medecinRepository.save(medecin);
        userRepository.save(user);

        // assert the result
        System.out.println(medecin.getMedecinId() + " | " + medecinResponse.getMedecinId());
        System.out.println(medecinRequest.getCin() + " | " + medecinResponse.getCin());
        System.out.println(medecinRequest.getNom() + " | " + medecinResponse.getNom());
        System.out.println(medecinRequest.getPrenom() + " | " + medecinResponse.getPrenom());
        System.out.println(medecinRequest.getRole() + " | " + medecinResponse.getRole());
        System.out.println(medecinRequest.getEmail() + " | " + medecinResponse.getEmail());

        assertEquals(medecin.getMedecinId(), medecinResponse.getMedecinId());
        assertEquals(medecinRequest.getCin(), medecinResponse.getCin());
        assertEquals(medecinRequest.getNom(), medecinResponse.getNom());
        assertEquals(medecinRequest.getPrenom(), medecinResponse.getPrenom());
        assertEquals(medecinRequest.getRole(), medecinResponse.getRole());
        assertEquals(medecinRequest.getEmail(), medecinResponse.getEmail());
    }
}