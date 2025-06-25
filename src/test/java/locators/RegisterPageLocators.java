package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle statischen Locators für die Registrierungsseite.
 */

public class RegisterPageLocators {
    public static final By GENDER_MALE = By.id("gender-male");
    public static final By FIRST_NAME_INPUT = By.id("FirstName");
    public static final By LAST_NAME_INPUT = By.id("LastName");
    public static final By EMAIL_INPUT = By.id("Email");
    public static final By PASSWORD_INPUT = By.id("Password");
    public static final By CONFIRM_PASSWORD_INPUT = By.id("ConfirmPassword");
    public static final By REGISTER_BUTTON = By.id("register-button");
    public static final By REGISTRATION_RESULT = By.cssSelector(".result");

    public static final By CONTINUE_BUTTON = By.cssSelector("input[value='Continue']");
}
