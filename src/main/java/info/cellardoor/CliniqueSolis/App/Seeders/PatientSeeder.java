package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Patient.Models.Antecedent;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Sexe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

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

        String groupeSanguin = faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        String allergies = Math.random() > 0.5 ? faker.food().ingredient() : "non allergique";
        String maladiesChroniques = Math.random() > 0.5 ? faker.medical().diseaseName() : "pas de maladies chroniques";
        String chirurgies = Math.random() > 0.5 ? faker.medical().diseaseName() : "pas d'informations";
        String antecedentsFamiliaux = Math.random() > 0.5 ? faker.medical().diseaseName() : "pas d'informations";
        String cin = faker.regexify("[A-Z]{2}[0-9]{5}");
        String telephone = faker.regexify("0[0-9]{9}");
//        YYYY-MM-DD with number padding, ty copilot
        String dateNaissance = faker.number().numberBetween(1931, 2023) + "-"
                + String.format("%02d", faker.number().numberBetween(1, 12)) + "-"
                + String.format("%02d", faker.number().numberBetween(1, 28));
        Sexe sexe = Math.random() > 0.5 ? Sexe.Homme : Sexe.Femme;
        String medicament = faker.medical().medicineName();
        String description = faker.medical().symptoms();
        String date = faker.date().birthday(0, 1).toString();

        Antecedent antecedent = Antecedent.builder()
                .groupeSanguin(groupeSanguin)
                .allergies(allergies)
                .maladiesChroniques(maladiesChroniques)
                .chirurgies(chirurgies)
                .antecedentsFamiliaux(antecedentsFamiliaux)
                .build();

        return Patient.builder()
                .user(user)
                .cin(cin)
                .dateNaissance(dateNaissance)
                .sexe(sexe)
                .telephone(telephone)
                .antecedents(antecedent)
                .build();
    }
}