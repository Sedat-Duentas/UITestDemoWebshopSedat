package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle statischen und dynamischen Locator-Definitionen für die Kategorieseiten.
 */

public class CategoryPageLocators {

    // Erfolgsbenachrichtigung nach dem Hinzufügen zum Warenkorb
    public static final By SUCCESS_NOTIFICATION = By.cssSelector(".bar-notification.success");

    // XPath-Template zum Finden des "Add to cart"-Buttons innerhalb des Produktcontainers, der einen Link mit dem angegebenen Produktnamen enthält (%s wird dynamisch ersetzt).
    private static final String ADD_TO_CART_BY_NAME_XPATH_TEMPLATE = "//div[contains(@class,'item-box')][.//a[text()='%s']]//input[contains(@class,'add-to-cart-button')]";

    // Gibt einen dynamischen Locator für den "Add to cart"-Button nach Produktnamen zurück
    public static By getAddToCartButtonByProductName(String productName) {
        return By.xpath(String.format(ADD_TO_CART_BY_NAME_XPATH_TEMPLATE, productName));
    }
}
