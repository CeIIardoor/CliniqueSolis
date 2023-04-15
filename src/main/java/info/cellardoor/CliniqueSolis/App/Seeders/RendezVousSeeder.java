package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Component
@Order(4)
public class RendezVousSeeder implements CommandLineRunner {
    private final Faker faker = LocalizedFakerFrench.getInstance();

    private final RendezVousRepository rendezVousRepository;

    public RendezVousSeeder(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int nbRendezVous = 5;

        for (int i = 0; i < nbRendezVous; i++) {

            User user_patient = UserSeeder.getSeed(faker);
            user_patient.setRole(Roles.ROLE_PATIENT);
            Patient patient = PatientSeeder.getSeed(faker, user_patient);

            User user_medecin = UserSeeder.getSeed(faker);
            user_patient.setRole(Roles.ROLE_MEDECIN);
            Medecin medecin = MedecinSeeder.getSeed(faker, user_medecin);

            RendezVous rdv = RendezVous.builder()
                    .patient(patient)
                    .medecin(medecin)
                    .duree(faker.options().option(15, 30, 45, 60))
                    .date(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis((long) (Math.random() * 365))))
                    .heure(new Time((long) (Math.random() * 24 * 60 * 60 * 1000)))
                    .build();
            rendezVousRepository.save(rdv);
        }
    }
}