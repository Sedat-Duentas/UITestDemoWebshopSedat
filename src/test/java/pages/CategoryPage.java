package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import locators.CategoryPageLocators;
import static utils.WaitUtils.waitForElementClickable;
import static utils.WaitUtils.waitForElementVisible;

/**
 * Page Object Klasse für die Kategorieseiten
 */
public class CategoryPage {
    private final WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Öffnet die Detailseite eines Produkts und gibt eine neue Instanz der ProductPage, die die geöffnete Seite repräsentiert.
    public ProductPage openProductDetails(String productName) {
        waitForElementClickable(driver, By.linkText(productName)).click();
        return new ProductPage(driver);
    }

    // Fügt ein Produkt direkt von der Kategorieseite in den Warenkorb.
    public void addProductToCartByName(String productName) {
        By addToCartButton = CategoryPageLocators.getAddToCartButtonByProductName(productName);
        WebElement button = waitForElementClickable(driver, addToCartButton);
        button.click();
        waitForElementVisible(driver, CategoryPageLocators.SUCCESS_NOTIFICATION);
    }
}