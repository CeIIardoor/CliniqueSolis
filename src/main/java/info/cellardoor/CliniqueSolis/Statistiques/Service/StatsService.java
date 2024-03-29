package info.cellardoor.CliniqueSolis.Statistiques.Service;

import info.cellardoor.CliniqueSolis.Consultation.Models.Consultation;
import info.cellardoor.CliniqueSolis.Consultation.Models.ConsultationRepository;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import info.cellardoor.CliniqueSolis.Statistiques.Model.Stats;
import info.cellardoor.CliniqueSolis.Statistiques.Model.StatsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class StatsService {
    private final StatsRepository statistiqueRepository;
    public final PatientRepository patientRepository;
    public final MedecinRepository medecinRepository;
    public final RendezVousRepository rendezVousRepository;
    public final ConsultationRepository consultationRepository;
    @Autowired
    public StatsService(StatsRepository statistiqueRepository,PatientRepository patientRepository,
                        MedecinRepository medecinRepository,RendezVousRepository rendezVousRepository,
                        ConsultationRepository consultationRepository) {
        this.statistiqueRepository = statistiqueRepository;
        this.patientRepository=patientRepository;
        this.medecinRepository=medecinRepository;
        this.rendezVousRepository=rendezVousRepository;
        this.consultationRepository=consultationRepository;


    }
public  List<Stats> getStats() {
        return statistiqueRepository.findAll();
    }

    public Map<Integer, Long> calculerNombrePatientsParAge() {
        List<Patient> patients = patientRepository.findAll();
        Map<Integer, Long> nombrePatientsParAge = new HashMap<>();

        for (Patient patient : patients) {
            int age = patient.getAge();
            nombrePatientsParAge.put(age, nombrePatientsParAge.getOrDefault(age, 0L) + 1);
        }

        return nombrePatientsParAge;
    }
    public Map<String, Long> calculerNombreRendezVousParDate() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        Map<String, Long> nombreRendezVousParDate = new HashMap<>();

        for (RendezVous rendezVous : rendezVousList) {
            String date = rendezVous.getDate();
            nombreRendezVousParDate.put(date, nombreRendezVousParDate.getOrDefault(date, 0L) + 1);
        }

        return nombreRendezVousParDate;
    }
    public Map<String, Long> calculerNombreMedecinsParSpecialite() {
        List<Medecin> medecins = medecinRepository.findAll();
        Map<String, Long> nombreMedecinsParSpecialite = new HashMap<>();

        for (Medecin medecin : medecins) {
            String specialite = medecin.getSpecialite();
            nombreMedecinsParSpecialite.put(specialite, nombreMedecinsParSpecialite.getOrDefault(specialite, 0L) + 1);
        }

        return nombreMedecinsParSpecialite;
    }
    public Map<String, Long> calculerNombreConsultationsParDate() {
        List<Consultation> consultations = consultationRepository.findAll();
        Map<String, Long> nombreConsultationsParDate = new HashMap<>();

        for (Consultation consultation : consultations) {
            String date = consultation.getDateConsultation();
            nombreConsultationsParDate.put(date, nombreConsultationsParDate.getOrDefault(date, 0L) + 1);
        }

        return nombreConsultationsParDate;
    }
    public Map<String, Double> calculatePercentagePatientsByAgeRange() {
        List<Patient> patients = patientRepository.findAll();
        Map<String, Long> countPatientsByAgeRange = new HashMap<>();
        Map<String, Double> percentagePatientsByAgeRange = new HashMap<>();

        // Initialize the age range categories
        String[] ageRanges = {"0-18", "19-40", "41-60", "60+"};

        // Initialize the count for each age range
        for (String ageRange : ageRanges) {
            countPatientsByAgeRange.put(ageRange, 0L);
        }

        // Count the number of patients within each age range
        for (Patient patient : patients) {
            int age = patient.getAge();
            if (age >= 0 && age <= 18) {
                countPatientsByAgeRange.put("0-18", countPatientsByAgeRange.get("0-18") + 1);
            } else if (age >= 19 && age <= 40) {
                countPatientsByAgeRange.put("19-40", countPatientsByAgeRange.get("19-40") + 1);
            } else if (age >= 41 && age <= 60) {
                countPatientsByAgeRange.put("41-60", countPatientsByAgeRange.get("41-60") + 1);
            } else {
                countPatientsByAgeRange.put("60+", countPatientsByAgeRange.get("60+") + 1);
            }
        }

        // Calculate the total number of patients
        long totalPatients = patients.size();

        // Calculate the percentage for each age range
        for (Map.Entry<String, Long> entry : countPatientsByAgeRange.entrySet()) {
            String ageRange = entry.getKey();
            long count = entry.getValue();
            double percentage = (count / (double) totalPatients) * 100;
            percentagePatientsByAgeRange.put(ageRange, percentage);
        }

        return percentagePatientsByAgeRange;
    }

}
