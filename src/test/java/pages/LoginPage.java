package pages;

import org.openqa.selenium.WebDriver;
import static locators.LoginPageLocators.*;
import static utils.WaitUtils.waitForElementClickable;
import static utils.WaitUtils.waitForElementVisible;

/**
 * Page Object für die Login-Seite. Ermöglicht die Anmeldung eines Benutzers über E-Mail und Passwort.
 */

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Führt den Login mit den angegebenen Zugangsdaten durch und gibt eine neue Instanz der HomePage zurück.
    public HomePage login(String email, String password) {
        waitForElementVisible(driver, EMAIL_FIELD).sendKeys(email);
        waitForElementVisible(driver, PASSWORD_FIELD).sendKeys(password);
        waitForElementClickable(driver, LOGIN_BUTTON).click();
        return new HomePage(driver);
    }
}
