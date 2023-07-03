package info.cellardoor.CliniqueSolis.Statistiques.Service;

import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.Statistiques.Http.Response.StatsMedecins;
import info.cellardoor.CliniqueSolis.Statistiques.Model.Stats;
import info.cellardoor.CliniqueSolis.Statistiques.Model.StatsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StatsService {
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository ;



    public StatsMedecins getNombreMedcin() {
        var counts =patientRepository.count();
        return null;
    }
    public StatsMedecins getNombreMedcin() {
        var counts =patientRepository.countByAttribute;
        return null;
    }
}
