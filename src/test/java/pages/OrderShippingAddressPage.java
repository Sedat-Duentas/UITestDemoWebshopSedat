package pages;

import static locators.OrderShippingAddressPageLocators.*;
import org.openqa.selenium.WebDriver;

import static utils.WaitUtils.waitForElementClickable;

/**
 * Page Object für die Versandadresse der Bestellungsseite - Schritt 2: Versandadresse.
 */

public class OrderShippingAddressPage {
    private final WebDriver driver;

    public OrderShippingAddressPage(WebDriver driver) {

        this.driver = driver;
    }

    // Klickt auf den "Continue"-Button, um zum nächsten Bestellschritt (Versandmethode) zu gelangen.
    public OrderShippingMethodPage continueToShippingMethod() {
        waitForElementClickable(driver, CONTINUE_BUTTON).click();
        return new OrderShippingMethodPage(driver);
    }
}