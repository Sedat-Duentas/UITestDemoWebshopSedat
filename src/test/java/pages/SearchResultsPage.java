package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import java.util.List;
import java.util.stream.Collectors;

import static locators.SearchResultsPageLocators.*;

/**
 * Page Object für die Suchergebnisseite. Kapselt die Aktion und Überprüfung auf der Suchergebnisseite.
 */
public class SearchResultsPage {
    private final WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Prüft, ob ein spezifisches Produkt in den Suchergebnissen angezeigt wird.
    public boolean isProductFound(String productName) {
        try {
            WaitUtils.waitForElementVisible(driver, getProductLinkByName(productName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Ruft alle Produkttitel aus den Suchergebnissen ab und gibt eine Liste von Strings, die die Texte der Produkttitel darstellen zurück.
    public List<String> getProductTitles() {
        WaitUtils.waitForElementVisible(driver, PRODUCT_TITLE_LINK);
        List<WebElement> productElements = driver.findElements(PRODUCT_TITLE_LINK);

        return productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Prüft, ob die Produkttitel in der erwarteten Reihenfolge sortiert sind.
    public boolean areProductsSortedByRelevance(List<String> expectedOrder) {
        List<String> actualOrder = getProductTitles();
        return actualOrder.equals(expectedOrder);
    }
}