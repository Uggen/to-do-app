package io.github.uggen.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {

    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("app/src/main/resources/application.properties")) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
