package pages;

import static locators.OrderPaymentInformationPageLocators.*;
import org.openqa.selenium.WebDriver;

import static utils.WaitUtils.waitForElementClickable;

/**
 * Page Object für die Eingabe von Zahlungsinformationen - Schritt 5
 */

public class OrderPaymentInformationPage {
    private final WebDriver driver;

    public OrderPaymentInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Klickt auf den "Continue"-Button und gibt eine neue Instanz von OrderConfirmPage.
    public OrderConfirmPage continueToConfirmOrder() {
        waitForElementClickable(driver, CONTINUE_BUTTON).click();
        return new OrderConfirmPage(driver);
    }
}