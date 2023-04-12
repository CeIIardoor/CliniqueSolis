package info.cellardoor.CliniqueSolis.Medecin.Service;

import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MedecinResponse getMedecinById(Integer id) {
        var medecin = medecinRepository.findByMedecinId(id).orElseThrow(() -> new NoSuchElementException("Medecin non trouvé"));
        return MedecinResponse.builder()
                .medecinId(medecin.getMedecinId())
                .cin(medecin.getCin())
                .specialite(medecin.getSpecialite())
                .diplome(medecin.getDiplome())
                .disponibilite(medecin.getDisponibilite())
                .build();
    }
    public MedecinResponse createMedecin(MedecinRequest medecinRequest) {
        var associatedUser = User.builder()
                .nom(medecinRequest.getNom())
                .prenom(medecinRequest.getPrenom())
                .email(medecinRequest.getEmail())
                .mdp(passwordEncoder.encode(medecinRequest.getMdp()))
                .role(Roles.ROLE_MEDECIN)
                .build();
        var medecin = Medecin.builder()
                .cin(medecinRequest.getCin())
                .specialite(medecinRequest.getSpecialite())
                .diplome(medecinRequest.getDiplome())
                .disponibilite(medecinRequest.getDisponibilite())
                .user(associatedUser)
                .build();
        var savedMedecin = medecinRepository.save(medecin);
        return MedecinResponse.builder()
                .medecinId(savedMedecin.getMedecinId())
                .cin(savedMedecin.getCin())
                .specialite(savedMedecin.getSpecialite())
                .diplome(savedMedecin.getDiplome())
                .disponibilite(savedMedecin.getDisponibilite())
                .userId(savedMedecin.getUser().getUserId())
                .nom(savedMedecin.getUser().getNom())
                .prenom(savedMedecin.getUser().getPrenom())
                .email(savedMedecin.getUser().getEmail())
                .role(savedMedecin.getUser().getRole().toString())
                .build();
    }
    public void deleteMedecinById(Integer id) {
        var medecin = medecinRepository.findByMedecinId(id)
                .orElseThrow(() -> new NoSuchElementException("Medecin non trouvé"));
        medecinRepository.delete(medecin);
    }

    public MedecinResponse updateMedecinById(Integer id, MedecinRequest medecinRequest) {
        var medecin = medecinRepository.findByMedecinId(id)
                .orElseThrow(() -> new NoSuchElementException("Medecin non trouvé"));
        var user = medecin.getUser();
        user.setNom(medecinRequest.getNom());
        user.setPrenom(medecinRequest.getPrenom());
        medecin.setCin(medecinRequest.getCin());
        medecin.setSpecialite(medecinRequest.getSpecialite());
        medecin.setDiplome(medecinRequest.getDiplome());
        medecin.setDisponibilite(medecinRequest.getDisponibilite());
        var savedUser = userRepository.save(user);
        var savedMedecin = medecinRepository.save(medecin);
        return MedecinResponse.builder()
                .medecinId(savedMedecin.getMedecinId())
                .cin(savedMedecin.getCin())
                .specialite(savedMedecin.getSpecialite())
                .diplome(savedMedecin.getDiplome())
                .disponibilite(savedMedecin.getDisponibilite())
                .userId(savedUser.getUserId())
                .nom(savedUser.getNom())
                .prenom(savedUser.getPrenom())
                .build();
    }
}