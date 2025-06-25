package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static locators.OrderPaymentMethodPageLocators.*;

import static utils.WaitUtils.waitForElementClickable;

/**
 * Page Object für die Zahlungsmethode in der Bestellung - Schritt 4: Zahlungsmethode.
 */
public class OrderPaymentMethodPage {
    private final WebDriver driver;

    public OrderPaymentMethodPage(WebDriver driver) {

        this.driver = driver;
    }

    // Wählt eine spezifische Zahlungsmethode basierend auf ihrer ID aus. optionId ist die ID der zu wählenden Zahlungsmethode (z.B. 0 für Cash on Delivery).
    public void selectPaymentMethod(int optionId) {
        By paymentMethodLocator = getPaymentMethodRadioLocator(optionId);
        waitForElementClickable(driver, paymentMethodLocator).click();
    }

    // Klickt auf den "Continue"-Button, um zu den Zahlungsinformationen zu gelangen und gibt eine neue Instanz von OrderPaymentInformationPage.
    public OrderPaymentInformationPage continueToPaymentInfo() {
        waitForElementClickable(driver, CONTINUE_BUTTON).click();
        return new OrderPaymentInformationPage(driver);
    }
}