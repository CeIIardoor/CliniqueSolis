package info.cellardoor.CliniqueSolis.RendezVous.Service;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    public final RendezVousRepository rendezVousRepository;

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
}