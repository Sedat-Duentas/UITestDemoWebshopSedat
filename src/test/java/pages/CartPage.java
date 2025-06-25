package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static locators.CartPageLocators.*;

import static utils.WaitUtils.waitForElementClickable;
import static utils.WaitUtils.waitForElementVisible;

import java.time.Duration;

/**
 * Page Object Klasse für den Warenkorb.
 */

public class CartPage {
    private final WebDriver driver;
    private final Duration TIMEOUT = Duration.ofSeconds(10); // Standard-Timeout für Wartebedingungen

    public CartPage(WebDriver driver) {

        this.driver = driver;
    }

    // Ruft die Anzahl der Produkte im Warenkorb aus dem Header ab und gibt die aktuelle Anzahl der Produkte im Warenkorb als Integer wieder.
    public int getCartQuantityFromHeader() {
        String qtyText = waitForElementVisible(driver, CART_QUANTITY).getText().replaceAll("[^0-9]", "");
        return qtyText.isEmpty() ? 0 : Integer.parseInt(qtyText);
    }

    // Prüft, ob ein Produkt mit dem angegebenen Namen im Warenkorb vorhanden ist.
    public boolean isProductInCart(String productName) {
        By productLocator = getProductNameInCartLocator(productName);
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(productLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Startet den Checkout-Prozess
    public OrderBillingAddressPage goToCheckout() {
        // AGB-Checkbox wird angeklickt
        waitForElementClickable(driver, TERMS_CHECKBOX).click();

        // Checkout-Button wird angeklickt
        waitForElementClickable(driver, CHECKOUT_BUTTON).click();

        // Gibt eine neue Instanz der OrderBillingAddressPage zurück
        return new OrderBillingAddressPage(driver);
    }
}