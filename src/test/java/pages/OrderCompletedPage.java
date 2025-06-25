package pages;

import static locators.OrderCompletedPageLocators.*;
import org.openqa.selenium.WebDriver;

import static utils.WaitUtils.waitForElementVisible;

/**
 * Page Object für die Bestellabschlussseite - Schritt 7: Bestellung abgeschlossen.
 */
public class OrderCompletedPage {

    private final WebDriver driver;

    public OrderCompletedPage(WebDriver driver) {
        this.driver = driver;
    }

    // Prüft, ob die Bestellung erfolgreich abgeschlossen wurde, basierend auf dem Erfolgstext.
    public boolean isOrderCompleted() {
        return waitForElementVisible(driver, SUCCESS_MESSAGE)
                .getText()
                .toLowerCase()
                .contains("successfully");
    }

    // Extrahiert die Bestellnummer von der Abschlussseite.
    public String getOrderNumber() {
        String text = waitForElementVisible(driver, ORDER_NUMBER).getText();
        if (text.toLowerCase().contains("order number")) {
            return text.replaceAll("[^0-9]", "");
        }
        return "Unknown";
    }
}