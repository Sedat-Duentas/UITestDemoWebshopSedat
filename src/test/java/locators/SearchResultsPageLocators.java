package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle Locators für die Suchergebnisseite.
 */
public class SearchResultsPageLocators {

    // Locator für Produktlink-Titel in Suchergebnissen.
    public static final By PRODUCT_TITLE_LINK = By.cssSelector(".product-item .details .product-title a");

    // Locator für einen spezifischen Produktlink in den Suchergebnissen, basierend auf dem Produktnamen.
    public static By getProductLinkByName(String productName) {
        // Sucht nach einem <a>-Tag, dessen normalisierter Text genau dem Produktnamen entspricht.
        return By.xpath("//a[normalize-space()='" + productName + "']");
    }
}