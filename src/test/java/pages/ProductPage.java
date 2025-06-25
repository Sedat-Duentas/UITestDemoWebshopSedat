package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import locators.ProductPageLocators;

/**
 * Page Object Klasse für die Produktseite (Detailsicht). Die Produkt-ID wird dynamisch von der Seite selbst ermittelt.
 */
public class ProductPage {
    private final WebDriver driver;
    private final int productId; // Die ID des Produkts, das diese Seite repräsentiert

    // Konstruktor für die ProductPage. Ermittelt die Produkt-ID beim Instanziieren des Objekts, indem sie aus der ID des "Add to cart"-Buttons auf der Seite gelesen wird.
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.productId = findProductIdOnPage(); // Die Produkt-ID wird hier ermittelt!
    }

    // Ermittelt die Produkt-ID, indem sie aus der ID des "Add to cart"-Buttons auf der aktuellen Seite extrahiert wird.
    private int findProductIdOnPage() {
        // Wartet, bis der "Add to cart"-Button sichtbar ist, um die Seite zu laden und das Element zu finden.
        WebElement addToCartButton = WaitUtils.waitForElementVisible(driver, ProductPageLocators.GENERIC_ADD_TO_CART_BUTTON);

        // Liest die Button-ID (z.B. "add-to-cart-button-13") und entfernt alle Nicht-Ziffern, um die Produkt-ID zu isolieren.
        String idAsString = addToCartButton.getAttribute("id").replaceAll("[^0-9]", "");

        // Konvertiert die extrahierte ID in eine Ganzzahl; gibt 0 zurück, falls keine Ziffern gefunden wurden.
        return idAsString.isEmpty() ? 0 : Integer.parseInt(idAsString);
    }

    // Prüft, ob das Produktbild angezeigt wird
    public boolean isProductImageVisible() {
        return driver.findElement(ProductPageLocators.getProductImageLocator(productId)).isDisplayed();
    }

    // Prüft, ob der Produkttitel sichtbar ist
    public boolean isProductTitleVisible() {
        return driver.findElement(ProductPageLocators.PRODUCT_TITLE).isDisplayed();
    }

    // Prüft, ob die Produktbeschreibung sichtbar ist
    public boolean isProductDescriptionVisible() {
        return driver.findElement(ProductPageLocators.PRODUCT_DESCRIPTION).isDisplayed();
    }

    // Prüft, ob der Produktpreis sichtbar ist
    public boolean isProductPriceVisible() {
        return driver.findElement(ProductPageLocators.getProductPriceLocator(productId)).isDisplayed();
    }

    // Prüft, ob der "Add to cart"-Button sichtbar ist
    public boolean isAddToCartButtonVisible() {
        return driver.findElement(ProductPageLocators.getAddToCartButtonLocator(productId)).isDisplayed();
    }

    // Fügt das Produkt, dieser Seite dem Warenkorb hinzu
    public void addToCart() {
        // Holt sich den passenden "Add to cart"-Locator für das Produkt dieser Seite
        By addToCartButtonLocator = ProductPageLocators.getAddToCartButtonLocator(productId);

        // Wartet darauf, dass der Button anklickbar ist, und holt sich dann das WebElement
        WebElement addToCartButton = WaitUtils.waitForElementClickable(driver, addToCartButtonLocator);

        // Klickt auf den Button
        addToCartButton.click();

        // Warte auf grüne Bestätigungsmeldung
        WaitUtils.waitForElementVisible(driver, ProductPageLocators.SUCCESS_NOTIFICATION);
    }
}