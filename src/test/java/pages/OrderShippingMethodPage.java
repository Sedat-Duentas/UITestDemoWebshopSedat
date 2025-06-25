package pages;

import static locators.OrderShippingMethodPageLocators.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.WaitUtils.waitForElementClickable;

/**
 * Page Object für die Versandmethode in der Bestellung - Schritt 3: Versandmethode.
 */

public class OrderShippingMethodPage {
    private final WebDriver driver;

    public OrderShippingMethodPage(WebDriver driver) {
        this.driver = driver;
    }

    // Wählt eine spezifische Versandmethode basierend auf ihrer ID aus. optionId ist die ID der zu wählenden Versandmethode (z.B. 1 für Ground Shipping, 2 für Next Day Air).
    public void selectShippingMethod(int optionId) {
        By shippingMethodLocator = getShippingMethodRadioLocator(optionId);
        waitForElementClickable(driver, shippingMethodLocator).click();
    }

    // Klickt auf den "Continue"-Button, um zum nächsten Bestellschritt (Zahlungsmethode) zu gelangen und gibt eine neue Instanz von OrderPaymentMethodPage.
    public OrderPaymentMethodPage continueToPaymentMethod() {
        waitForElementClickable(driver, CONTINUE_BUTTON).click();
        return new OrderPaymentMethodPage(driver);
    }
}