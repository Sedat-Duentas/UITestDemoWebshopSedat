package pages;

import static locators.OrderConfirmPageLocators.*;
import org.openqa.selenium.WebDriver;

import static utils.WaitUtils.waitForElementClickable;

/**
 * Page Object für die Bestellbestätigungsseite - Schritt 6
 */
public class OrderConfirmPage {

    private final WebDriver driver;

    public OrderConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    // Klickt auf den "Confirm"-Button und gibt eine neue Instanz von OrderCompletedPage.
    public OrderCompletedPage confirmOrder() {
        waitForElementClickable(driver, CONFIRM_BUTTON).click();
        return new OrderCompletedPage(driver);
    }
}