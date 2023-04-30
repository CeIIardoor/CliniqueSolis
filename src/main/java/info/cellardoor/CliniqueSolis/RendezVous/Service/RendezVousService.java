package info.cellardoor.CliniqueSolis.RendezVous.Service;

import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Request.RendezVousRequest;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    public final RendezVousRepository rendezVousRepository;
    public final PatientRepository PatientRepository;
    public final MedecinRepository MedecinRepository;

    public ListRendezVousResponse getAll() {
        List<RendezVous> listeRendezVous = rendezVousRepository.findAll();
        return rendezVousDTO(listeRendezVous);
    }

    public ListRendezVousResponse getByDate(String date) {
        List<RendezVous> listeRendezVous = rendezVousRepository.findByDate(date);
        return rendezVousDTO(listeRendezVous);
    }

    public ListRendezVousResponse getByPartialDate(String date) {
        List<RendezVous> listeRendezVous = rendezVousRepository.findByDateStartingWith(date);
        return rendezVousDTO(listeRendezVous);
    }

    private ListRendezVousResponse rendezVousDTO(List<RendezVous> listeRendezVous) {
        if (listeRendezVous.size() == 0)
            return null;
        return ListRendezVousResponse.builder()
                .rendezVous(listeRendezVous.stream().map(rendezVous -> RendezVousResponse.builder()
                                .rendezVousId(rendezVous.getRendezVousId())
                                .patientId(rendezVous.getPatient().getPatientId())
                                .medecinId(rendezVous.getMedecin().getMedecinId())
                                .date(rendezVous.getDate())
                                .heure(rendezVous.getHeure())
                                .duree(rendezVous.getDuree())
                                .build())
                        .toList())
                .build();
    }
    public RendezVousResponse  getRendezVousById(Integer id) {
        var rdv = rendezVousRepository.findByRendezVousId(id).orElseThrow(() -> new NoSuchElementException("Rendez Vous non trouvé"));
        return RendezVousResponse.builder()
                .rendezVousId(rdv.getRendezVousId())
                .patientId(rdv.getPatient().getPatientId())
                .medecinId(rdv.getMedecin().getMedecinId())
                .date(rdv.getDate())
                .heure(rdv.getHeure())
                .build();
    }
    private Patient getPatientById(Integer patientId) {
        Optional<Patient> patient = PatientRepository.findById(patientId);
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("Patient not found");
        }
        return patient.get();
    }

    private Medecin getMedecinById(Integer medecinId) {
        Optional<Medecin> medecin = MedecinRepository.findById(medecinId);
        if (medecin.isEmpty()) {
            throw new IllegalArgumentException("Medecin not found");
        }
        return medecin.get();
    }
    public RendezVousResponse createRendezVous(RendezVousRequest rendezVousRequest) {
        Patient patient = getPatientById(rendezVousRequest.getPatientId());
        Medecin medecin = getMedecinById(rendezVousRequest.getMedecinId());

        RendezVous rendezVous = RendezVous.builder()
                .patient(patient)
                .medecin(medecin)
                .date(rendezVousRequest.getDate())
                .heure(rendezVousRequest.getHeure())
                .duree(rendezVousRequest.getDuree())
                .build();
        var rdv= rendezVousRepository.save(rendezVous);
        return RendezVousResponse.builder()
                .rendezVousId(rdv.getRendezVousId())
                .patientId(rdv.getPatient().getPatientId())
                .medecinId(rdv.getMedecin().getMedecinId())
                .date(rdv.getDate())
                .heure(rdv.getHeure())
                .duree(rdv.getDuree())
                .build();
    }
//    public RendezVous createRendezVous(RendezVousRequest rendezVousRequest) {
//        Patient patient = getPatientById(rendezVousRequest.getPatientId());
//        Medecin medecin = getMedecinById(rendezVousRequest.getMedecinId());
//
//        RendezVous rendezVous = RendezVous.builder()
//                .patient(patient)
//                .medecin(medecin)
//                .date(rendezVousRequest.getDate())
//                .heure(rendezVousRequest.getHeure())
//                .duree(rendezVousRequest.getDuree())
//                .build();
//        return rendezVousRepository.save(rendezVous);
//    }

    public RendezVousResponse updateRendezVousById(Integer id, RendezVousRequest rdvRequest) {
        var rdv = rendezVousRepository.findByRendezVousId(id)
                .orElseThrow(() -> new NoSuchElementException("Rendez Vous  non trouvé"));
        rdv.setPatient(getPatientById(rdvRequest.getPatientId()));
        rdv.setMedecin(getMedecinById(rdvRequest.getMedecinId()));
        rdv.setDate(rdvRequest.getDate());
        rdv.setHeure(rdvRequest.getHeure());
        rdv.setDuree(rdvRequest.getDuree());
        var savedRdv= rendezVousRepository.save(rdv);
        return RendezVousResponse.builder()
                .rendezVousId(rdv.getRendezVousId())
                .patientId(rdv.getPatient().getPatientId())
                .medecinId(rdv.getMedecin().getMedecinId())
                .date(rdv.getDate())
                .heure(rdv.getHeure())
                .build();
    }
    public RendezVous updateRendezVous(Integer rendezVousId, RendezVousRequest rendezVousRequest) {
        Optional<RendezVous> optionalRendezVous = rendezVousRepository.findById(rendezVousId);
        if (optionalRendezVous.isEmpty()) {
            return null;
        }
        Optional<Patient> patient = PatientRepository.findById(rendezVousRequest.getPatientId());
        if (patient.isEmpty()) {
            return null;
        }
        Optional<Medecin> medecin = MedecinRepository.findById(rendezVousRequest.getMedecinId());
        if (medecin.isEmpty()) {
            return null;
        }
        RendezVous rendezVous = optionalRendezVous.get();
        rendezVous.setPatient(patient.get());
        rendezVous.setMedecin(medecin.get());
        rendezVous.setDate(rendezVousRequest.getDate());
        rendezVous.setHeure(rendezVousRequest.getHeure());
        rendezVous.setDuree(rendezVousRequest.getDuree());
        return rendezVousRepository.save(rendezVous);
    }
    public void deleteRendezVousById(Integer rendezVousId) {
        var rdv = rendezVousRepository.findByRendezVousId(rendezVousId)
                .orElseThrow(() -> new NoSuchElementException("Rendez-vous with id " + rendezVousId + " not found"));
        rendezVousRepository.delete(rdv);
    }
    public void deleteRendezVous(Integer rendezVousId) {
        Optional<RendezVous> optionalRendezVous = rendezVousRepository.findById(rendezVousId);
        if (optionalRendezVous.isEmpty()) {
            throw new EntityNotFoundException("Rendez-vous with id " + rendezVousId + " not found");
        }
        RendezVous rendezVous = optionalRendezVous.get();
        rendezVousRepository.delete(rendezVous);
    }

    public ListRendezVousResponse findRendezVousByDate(String date) {
        List<RendezVous> rendezVous = rendezVousRepository.findByDateStartingWith(date);
        return ListRendezVousResponse.builder()
                .rendezVous(rendezVous.stream()
                        .map(rdv -> RendezVousResponse.builder()
                                .rendezVousId(rdv.getRendezVousId())
                                .patientId(rdv.getPatient().getPatientId())
                                .medecinId(rdv.getMedecin().getMedecinId())
                                .date(rdv.getDate())
                                .heure(rdv.getHeure())
                                .build())
                        .toList())
                .build();
    }
    public RendezVousResponse getRendezVousResponse(RendezVousRequest rendezVousRequest, RendezVous rendezVous) {
        BeanUtils.copyProperties(rendezVousRequest, rendezVous, getNullPropertyNames(rendezVousRequest));
        var savedRdv = rendezVousRepository.save(rendezVous);
        return RendezVousResponse.builder()
                .rendezVousId(savedRdv.getRendezVousId())
                .patientId(savedRdv.getPatient().getPatientId())
                .medecinId(savedRdv.getMedecin().getMedecinId())
                .date(savedRdv.getDate())
                .heure(savedRdv.getHeure())
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
}