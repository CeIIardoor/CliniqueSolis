package info.cellardoor.CliniqueSolis.RendezVous.Service;

import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    public final RendezVousRepository rendezVousRepository;

    public ListRendezVousResponse getAll() {
        List<RendezVous> listeRendezVous = rendezVousRepository.findAll();
        if (listeRendezVous.size() == 0)
            return null;
        return ListRendezVousResponse.builder()
                .rendezVous(listeRendezVous.stream().map(rendezVous -> RendezVousResponse.builder()
                                .rendezVousId(rendezVous.getRendezVousId())
                                .patient(rendezVous.getPatient())
                                .medecin(rendezVous.getMedecin())
                                .date(rendezVous.getDate())
                                .build())
                        .toList())
                .build();
    }



    public ListRendezVousResponse getByYear(Integer year) {
        List<RendezVous> listeRendezVous = rendezVousRepository.findByDateBetween(Date.valueOf(LocalDate.of(year, 1, 1)), Date.valueOf(LocalDate.of(year, 12, 31)));
        if (listeRendezVous.size() == 0)
            return null;
        return ListRendezVousResponse.builder()
                .rendezVous(listeRendezVous.stream().map(rendezVous -> RendezVousResponse.builder()
                                .rendezVousId(rendezVous.getRendezVousId())
                                .patient(rendezVous.getPatient())
                                .medecin(rendezVous.getMedecin())
                                .date(rendezVous.getDate())
                                .build())
                        .toList())
                .build();
    }

    public ListRendezVousResponse getByMonth(Integer year, Integer month) {
        List<RendezVous> listeRendezVous = rendezVousRepository.findByDateBetween(
                Date.valueOf(LocalDate.of(year, month, 1)),
                Date.valueOf(LocalDate.of(year, month, 31))
        );
        if (listeRendezVous.size() == 0)
            return null;
        return ListRendezVousResponse.builder()
                .rendezVous(listeRendezVous.stream().map(rendezVous -> RendezVousResponse.builder()
                                .rendezVousId(rendezVous.getRendezVousId())
                                .patient(rendezVous.getPatient())
                                .medecin(rendezVous.getMedecin())
                                .date(rendezVous.getDate())
                                .build())
                        .toList())
                .build();
    }

    public ListRendezVousResponse getByDate(Integer year, Integer month, Integer day) {
        List<RendezVous> listeRendezVous = rendezVousRepository.findByDate(Date.valueOf(LocalDate.of(year, month, day)));
        if (listeRendezVous.size() == 0)
            return null;
        return ListRendezVousResponse.builder()
                .rendezVous(listeRendezVous.stream().map(rendezVous -> RendezVousResponse.builder()
                                .rendezVousId(rendezVous.getRendezVousId())
                                .patient(rendezVous.getPatient())
                                .medecin(rendezVous.getMedecin())
                                .date(rendezVous.getDate())
                                .build())
                        .toList())
                .build();
    }
}