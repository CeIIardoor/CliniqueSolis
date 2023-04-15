package info.cellardoor.CliniqueSolis.App.Seeders;


import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Order(1)
public class UsersSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final Faker faker = new Faker(new Locale("fr", "FR"));
    private final User admin = User.getAdminInstance("Elliot", "Alderson", "e.alderson@solis.ma", "123456");
    public UsersSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(admin);

        int nbUsers = 5;

        for (int i = 0; i < nbUsers; i++) {
            User user = getSeed(faker, new BCryptPasswordEncoder());
            userRepository.save(user);
        }
    }

    static User getSeed(Faker faker, PasswordEncoder passwordEncoder) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@solis.ma";
        String password = passwordEncoder.encode("123456");
        return new User(firstName, lastName, email, password);
    }
}