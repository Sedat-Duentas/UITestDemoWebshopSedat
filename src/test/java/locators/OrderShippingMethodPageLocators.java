package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle Locators für die Versandmethode in der Bestellung.
 */

public class OrderShippingMethodPageLocators {
    // Dynamischer-Locator-Template für die Radio-Optionen der Versandmethoden
    private static final String SHIPPING_METHOD_RADIO_ID_TEMPLATE = "shippingoption_%d";

    // Locator für den "Continue"-Button auf der Versandmethoden-Seite.
    public static final By CONTINUE_BUTTON = By.cssSelector("input[class='button-1 shipping-method-next-step-button']");

    // Gibt einen Locator für eine spezifische Versandmethode (Radio-Option) basierend auf ihrer ID zurück.
    public static By getShippingMethodRadioLocator(int optionId) {
        return By.id(String.format(SHIPPING_METHOD_RADIO_ID_TEMPLATE, optionId));
    }
}