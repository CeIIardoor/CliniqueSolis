package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class PatientsSeeder implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final Faker faker = LocalizedFakerFrench.getInstance();

    public PatientsSeeder(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        int nbPatients = 5;

        for (int i = 0; i < nbPatients; i++) {
            User user = UsersSeeder.getSeed(faker);
            user.setRole(Roles.ROLE_PATIENT);

            String groupeSanguin = faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
            String allergies = Math.random() > 0.5 ? faker.food().ingredient() : null;
            String maladiesChroniques = Math.random() > 0.5 ? faker.medical().diseaseName() : null;
            String chirurgies = Math.random() > 0.5 ? faker.medical().diseaseName() : null;
            String antecedentsFamiliaux = Math.random() > 0.5 ? faker.medical().diseaseName() : null;
            String cin = faker.regexify("[A-Z]{2}[0-9]{5}");

            Patient patient = Patient.builder()
                    .user(user)
                    .groupeSanguin(groupeSanguin)
                    .allergies(allergies)
                    .maladiesChroniques(maladiesChroniques)
                    .chirurgies(chirurgies)
                    .antecedentsFamiliaux(antecedentsFamiliaux)
                    .cin(cin)
                    .build();

            patientRepository.save(patient);
        }
    }
}