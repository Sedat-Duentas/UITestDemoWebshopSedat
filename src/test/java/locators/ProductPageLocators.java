package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle Locator-Definitionen für die Produktseite
 */
public class ProductPageLocators {

    // Generischer Locator für den "Add to cart"-Button, um dessen ID zu lesen
    public static final By GENERIC_ADD_TO_CART_BUTTON = By.cssSelector("input[id^='add-to-cart-button-']");

    // Statische Locators, die nicht von der Produkt-ID abhängen
    public static final By PRODUCT_TITLE = By.cssSelector("h1[itemprop='name']");
    public static final By PRODUCT_DESCRIPTION = By.cssSelector(".short-description");
    public static final By SUCCESS_NOTIFICATION = By.cssSelector(".bar-notification.success");

    // String-Templates für dynamische Locators, die eine Produkt-ID benötigen
    private static final String PRODUCT_IMAGE_ID_TEMPLATE = "main-product-img-%d";
    private static final String PRODUCT_PRICE_CSS_TEMPLATE = ".price-value-%d";
    private static final String ADD_TO_CART_BUTTON_ID_TEMPLATE = "add-to-cart-button-%d";

    // Locator für das Produktbild
    public static By getProductImageLocator(int productId) {
        return By.id(String.format(PRODUCT_IMAGE_ID_TEMPLATE, productId));
    }

    // Locator für den Produktpreis
    public static By getProductPriceLocator(int productId) {
        return By.cssSelector(String.format(PRODUCT_PRICE_CSS_TEMPLATE, productId));
    }

    // Locator für den "Add to cart"-Button
    public static By getAddToCartButtonLocator(int productId) {
        return By.id(String.format(ADD_TO_CART_BUTTON_ID_TEMPLATE, productId));
    }
}