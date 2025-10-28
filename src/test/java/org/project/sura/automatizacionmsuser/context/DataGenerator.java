package org.project.sura.automatizacionmsuser.context;
import net.datafaker.Faker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("es", "CO"));

    public static Map<String, Object> generateUserData() {
        Map<String, Object> user = new HashMap<>();

        user.put("username", faker.internet().emailAddress());
        user.put("password", faker.internet().password(8, 16));
        user.put("firstname", faker.name().firstName());
        user.put("lastname", faker.name().lastName());
        user.put("country", faker.address().country());

        return user;
    }
}
