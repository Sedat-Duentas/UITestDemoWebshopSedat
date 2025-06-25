package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle Locators für den Warenkorb.
 */

public class CartPageLocators {
    public static final By CART_QUANTITY = By.className("cart-qty");
    public static final By TERMS_CHECKBOX = By.id("termsofservice");
    public static final By CHECKOUT_BUTTON = By.id("checkout");

    // Locator für einen Produktnamen-Link im Warenkorb, basierend auf Produktname und Klasse.
    public static By getProductNameInCartLocator(String productName) {
        // Dieser XPath sucht nach einem <a>-Element mit der Klasse 'product-name', dessen Text genau dem 'productName' entspricht (Leerzeichen werden normalisiert).
        return By.xpath("//a[@class='product-name' and normalize-space()='" + productName + "']");
    }
}