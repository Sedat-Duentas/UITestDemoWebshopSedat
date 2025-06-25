package locators;

import org.openqa.selenium.By;

/**
 * Enthält den Locator für die Zahlungsinformationen in der Bestellung.
 */
public class OrderPaymentInformationPageLocators {
    public static final By CONTINUE_BUTTON = By.cssSelector("input[class='button-1 payment-info-next-step-button']");
}