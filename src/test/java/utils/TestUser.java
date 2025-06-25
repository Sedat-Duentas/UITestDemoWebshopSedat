package utils;

import java.util.UUID;

/**
 * Repräsentiert einen Testnutzer mit festen Stammdaten und dynamisch generierter E-Mail-Adresse.
 */

public class TestUser {

    public final String firstName = "Max";
    public final String lastName = "Mustermann";
    public final String email;
    public final String password = "Test1234!";
    public final String country = "Germany";
    public final String city = "Berlin";
    public final String address = "Musterstraße 1";
    public final String zipCode = "10115";
    public final String phoneNumber = "030123456";

    // Generierung einer dynamischen E-Mail-Adresse mit UUID.randomUUID()
    public TestUser() {
        email = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }
}
