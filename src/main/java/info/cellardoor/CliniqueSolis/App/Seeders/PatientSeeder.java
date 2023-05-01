package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Patient.Models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;

@Component
@Order(2)
public class PatientSeeder implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();

    public PatientSeeder(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int nbPatients = 5;

        for (int i = 0; i < nbPatients; i++) {
            User user = UserSeeder.getSeed(frenchFaker);
            user.setRole(Roles.ROLE_PATIENT);
            Patient patient = getSeed(frenchFaker, user);
            Antecedent antecedent = patient.getAntecedents();
            patient.setAntecedents(antecedent);
            patientRepository.save(patient);
        }
    }

    static Patient getSeed(Faker faker, User user) {

        LocalDate dateNaissance = faker.date().birthday(10, 70).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String groupeSanguin = faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        String allergies = Math.random() > 0.5 ? faker.food().ingredient() : "non allergique";
        String maladiesChroniques = Math.random() > 0.5 ? faker.medical().diseaseName() : "pas de maladies chroniques";
        String chirurgies = Math.random() > 0.5 ? faker.medical().diseaseName() : "pas d'informations";
        String antecedentsFamiliaux = Math.random() > 0.5 ? faker.medical().diseaseName() : "pas d'informations";
        String cin = faker.regexify("[A-Z]{2}[0-9]{5}");
        String telephone = faker.regexify("0[0-9]{9}");
        String sexe = faker.options().option("Homme", "Femme");
        String medicaments = Math.random() > 0.5 ? faker.medical().medicineName() : "pas de medicaments";
        String description = faker.medical().symptoms();

        Ordonnance ordonnance = Ordonnance.builder()
                .medicaments(medicaments)
                .description(description)
                .build();
        Antecedent antecedent = Antecedent.builder()
                .groupeSanguin(groupeSanguin)
                .allergies(allergies)
                .maladiesChroniques(maladiesChroniques)
                .chirurgies(chirurgies)
                .antecedentsFamiliaux(antecedentsFamiliaux)
                .ordonnances(Collections.singletonList(ordonnance))
                .build();

        Patient patient = Patient.builder()
                .user(user)
                .cin(cin)
                .dateNaissance(dateNaissance)
                .sexe(Sexe.valueOf(sexe))
                .telephone(telephone)
                .build();

        patient.setAntecedents(antecedent);
        antecedent.setOrdonnances(Collections.singletonList(ordonnance));

        return patient;
    }

}