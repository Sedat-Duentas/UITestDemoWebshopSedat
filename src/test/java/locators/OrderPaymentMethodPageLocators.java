package locators;

import org.openqa.selenium.By;

/**
 * Enthält Locators für die Zahlungsmethode in der Bestellung.
 */
public class OrderPaymentMethodPageLocators {

    // Dynamischer-Locator-Template für die Radio-Optionen der Zahlungsmethoden
    private static final String PAYMENT_METHOD_RADIO_ID_TEMPLATE = "paymentmethod_%d";

    // Locator für den "Continue"-Button auf der Zahlungsmethoden-Seite.
    public static final By CONTINUE_BUTTON = By.cssSelector("input[class='button-1 payment-method-next-step-button']");

    // Gibt einen Locator für eine spezifische Zahlungsmethode (Radio-Option) basierend auf ihrer ID zurück.(z.B. 0 für Cash on Delivery).
    public static By getPaymentMethodRadioLocator(int optionId) {
        return By.id(String.format(PAYMENT_METHOD_RADIO_ID_TEMPLATE, optionId));
    }
}