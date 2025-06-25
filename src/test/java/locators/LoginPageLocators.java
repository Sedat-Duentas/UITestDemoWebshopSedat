package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle statischen Locators für die Loginseite.
 */

public class LoginPageLocators {
    public static final By EMAIL_FIELD = By.id("Email");
    public static final By PASSWORD_FIELD = By.id("Password");
    public static final By LOGIN_BUTTON = By.cssSelector("input.login-button");
}
