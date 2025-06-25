package locators;

import org.openqa.selenium.By;

/**
 * Enthält den Locator für die Versandadresse in der Bestellung.
 */

public class OrderShippingAddressPageLocators {

    public static final By CONTINUE_BUTTON = By.cssSelector("input[onclick='Shipping.save()']");
}