package info.cellardoor.CliniqueSolis.App.Config;

import com.github.javafaker.Faker;

import java.util.Locale;

public class LocalizedFaker {
    private static Faker instance;
    public static Faker getInstance() {
        if (instance == null) {
            return new Faker(new Locale("fr", "FR"));
        }
        return instance;
    }
}