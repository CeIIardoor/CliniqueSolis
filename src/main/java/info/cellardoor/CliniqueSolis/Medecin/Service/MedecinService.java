package info.cellardoor.CliniqueSolis.Medecin.Service;
import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.ListMedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.ListPatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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
                .telephone(medecin.getTelephone())
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
                .telephone(medecinRequest.getTelephone())
                .user(associatedUser)
                .build();
        var savedMedecin = medecinRepository.save(medecin);
        return MedecinDTO.build(savedMedecin);
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
        medecin.setTelephone(medecinRequest.getTelephone());
        var savedUser = userRepository.save(user);
        var savedMedecin = medecinRepository.save(medecin);
        return MedecinDTO.build(savedMedecin);
    }
    public ListMedecinResponse findByCinStartingWith(String cin) {
        List<Medecin> medecins = medecinRepository.findByCinStartingWith(cin.toUpperCase());
        if (medecins.size() == 0)
            return null;
        return ListMedecinResponse.builder()
                .medecins(medecins.stream().map(medecin -> MedecinResponse.builder()
                                .medecinId(medecin.getMedecinId())
                                .nom(medecin.getUser().getNom())
                                .prenom(medecin.getUser().getPrenom())
                                .cin(medecin.getCin())
                                .email(medecin.getUser().getEmail())
                                .role(medecin.getUser().getRole().toString())
                                .specialite(medecin.getSpecialite())
                                .diplome(medecin.getDiplome())
                                .telephone(medecin.getTelephone())
                                .build())
                        .toList())
                .build();
    }
    public ListMedecinResponse findBySpecialiteStartingWithOrCinStartingWith(String specialite, String cin) {
        List<Medecin> medecins = medecinRepository.findBySpecialiteStartingWithOrCinStartingWith(specialite.toUpperCase(), cin.toUpperCase());
        if (medecins.size() == 0)
            return null;
        return ListMedecinResponse.builder()
                .medecins(medecins.stream().map(medecin -> MedecinResponse.builder()
                                .medecinId(medecin.getMedecinId())
                                .nom(medecin.getUser().getNom())
                                .prenom(medecin.getUser().getPrenom())
                                .cin(medecin.getCin())
                                .email(medecin.getUser().getEmail())
                                .role(medecin.getUser().getRole().toString())
                                .specialite(medecin.getSpecialite())
                                .diplome(medecin.getDiplome())
                                .telephone(medecin.getTelephone())
                                .build())
                        .toList())
                .build();
    }
    public ListMedecinResponse findBySpecialiteStartingWith(String specialite) {
        List<Medecin> medecins = medecinRepository.findBySpecialiteStartingWith(specialite.toUpperCase());
        if (medecins.size() == 0)
            return null;
        return ListMedecinResponse.builder()
                .medecins(medecins.stream().map(medecin -> MedecinResponse.builder()
                                .medecinId(medecin.getMedecinId())
                                .nom(medecin.getUser().getNom())
                                .prenom(medecin.getUser().getPrenom())
                                .cin(medecin.getCin())
                                .email(medecin.getUser().getEmail())
                                .role(medecin.getUser().getRole().toString())
                                .specialite(medecin.getSpecialite())
                                .diplome(medecin.getDiplome())
                                .telephone(medecin.getTelephone())
                                .build())
                        .toList())
                .build();
    }

    public MedecinResponse getMedecinResponse(MedecinRequest medecinRequest, Medecin medecin) {
        BeanUtils.copyProperties(medecinRequest, medecin, getNullPropertyNames(medecinRequest));
        var savedMedecin = medecinRepository.save(medecin);
        var user = medecin.getUser();
        BeanUtils.copyProperties(medecinRequest, user, getNullPropertyNames(medecinRequest));
        var savedUser = userRepository.save(user);
        return MedecinDTO.build(savedMedecin);
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




    public ListMedecinResponse getAll() {
        var medecins = medecinRepository.findAll();
        if (medecins.size() == 0)
            return null;
        return ListMedecinResponse.builder()
                .medecins(medecins.stream().map(medecin -> MedecinResponse.builder()
                                .medecinId(medecin.getMedecinId())
                                .cin(medecin.getCin())
                                .nom(medecin.getUser().getNom())
                                .prenom(medecin.getUser().getPrenom())
                                .email(medecin.getUser().getEmail())
                                .role(medecin.getUser().getRole().toString())
                                .specialite(medecin.getSpecialite())
                                .diplome(medecin.getDiplome())
                                .telephone(medecin.getTelephone())
                                .build())
                        .toList())
                .build();
    }

}