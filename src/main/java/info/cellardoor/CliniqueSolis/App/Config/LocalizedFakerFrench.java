package info.cellardoor.CliniqueSolis.App.Config;

import com.github.javafaker.Faker;

import java.util.Locale;

public class LocalizedFakerFrench {
    private static Faker instance;
    public static void setInstance(Faker instance) {
        LocalizedFakerFrench.instance = instance;
    }
    public static Faker getInstance() {
        if (instance == null) {
            setInstance(new Faker(new Locale("fr", "FR")));
            return instance;
        }
        return instance;
    }
}