package info.cellardoor.CliniqueSolis.App.Seeders;


import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Order(2)
public class PatientsSeeder implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final Faker faker = new Faker(new Locale("fr", "FR"));

    public PatientsSeeder(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        int nbPatients = 5;

        for (int i = 0; i < nbPatients; i++) {
            User user = UsersSeeder.getSeed(faker, new BCryptPasswordEncoder());
            user.setRole(Roles.ROLE_PATIENT);

            String groupeSanguin = faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
            String allergies = faker.food().ingredient();
            String maladiesChroniques = faker.medical().diseaseName();
            String chirurgies = faker.medical().symptoms();
            String antecedentsFamiliaux = faker.medical().diseaseName();
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