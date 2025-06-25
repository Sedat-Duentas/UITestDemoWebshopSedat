package tests.userstory3;

import base.TestSetup;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RegisterPage;
import utils.TestUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testfall für User Story 3:
 * Als neuer Benutzer möchte ich ein Benutzerkonto erstellen können damit ich später bestellen und meine Käufe einsehen kann.
 * Akzeptanzkriterien:
 * - Die Schaltfläche „Register“ ist im Header verfügbar.
 * - Das Formular fragt Pflichtfelder ab (z.B. E-Mail, Passwort).
 * - Nach erfolgreicher Registrierung werde ich automatisch eingeloggt.
 */

public class CheckRegisterTest extends TestSetup {

    @Test
    public void testUserCanRegisterSuccessfully() {

        // --- Arrange ---
        HomePage homePage = new HomePage(driver);
        TestUser user = new TestUser(); // generiert eindeutige Nutzerdaten

        // --- Act --- Registrierungsprozess
        RegisterPage registerPage = homePage.goToRegisterPage(); // 1. Navigiere zur Registrierungsseite
        registerPage.fillRegistrationForm(user);    // 2. Registrierungsformular ausfüllen
        registerPage.clickRegisterButton(); // 3. Klicke auf den Registrieren-Button (bleibt auf der Seite mit Erfolgsmeldung)

        // --- Assert --- Prüfe den Erfolg der Registrierung auf der Bestätigungsseite
        assertTrue(registerPage.isRegistrationSuccessful(), "Registrierung war nicht erfolgreich.");

        // --- Act --- Weiter zur Homepage
        homePage = registerPage.clickContinueButton();

        // --- Assert --- Prüfe, ob der Benutzer auf der Homepage eingeloggt ist
        assertTrue(homePage.isUserLoggedIn(), "Benutzer wurde nach der Registrierung nicht eingeloggt.");
    }
}