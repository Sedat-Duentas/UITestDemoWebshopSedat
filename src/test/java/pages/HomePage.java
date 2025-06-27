package pages;

import base.TestSetup;
import org.openqa.selenium.WebDriver;
import static locators.HomePageLocators.*;
import static utils.WaitUtils.*;

/**
 * Page Object Klasse für die Startseite (HomePage). Kapselt alle Aktionen, die auf der Startseite möglich sind.
 */
public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    public HomePage navigateToHomePage() {
        driver.get(TestSetup.BASE_URL);
        return new HomePage(driver);
    }

    // Navigiert zur angegebenen Kategorie über das Hauptmenü. (z.B. "Books", "Computers") und gibt eine neue Instanz der CategoryPage.
    public CategoryPage navigateToCategory(String categoryName) {
        waitForElementClickable(driver, categoryLink(categoryName)).click();
        return new CategoryPage(driver);
    }

    // Navigiert zur Registrierungs-Seite und gibt eine neue Instanz der RegisterPage.
    public RegisterPage goToRegisterPage() {
        waitForElementClickable(driver, REGISTER_LINK).click();
        return new RegisterPage(driver);
    }

    // Navigiert zur Login-Seite. Wartet auf Benachrichtigungen, um Klicks zu ermöglichen.
    public LoginPage goToLoginPage() {
        waitForElementNotVisible(driver, BAR_NOTIFICATION_SUCCESS);
        waitForElementClickable(driver, LOGIN_LINK).click();
        return new LoginPage(driver);
    }

    // Führt einen Logout durch und gibt eine neue Instanz der HomePage.
    public HomePage logout() {
        waitForElementClickable(driver, LOGOUT_LINK).click();
        return new HomePage(driver);
    }

    /// Öffnet die Warenkorb-Seite. Wartet auf Benachrichtigungen, um Klicks zu ermöglichen.
    public CartPage goToCart() {
        waitForElementNotVisible(driver, BAR_NOTIFICATION_SUCCESS);
        waitForElementClickable(driver, CART_LINK).click();
        return new CartPage(driver);
    }

    // Prüft, ob das Suchfeld im Header sichtbar ist, return true, wenn das Suchfeld sichtbar ist, sonst false.
    public boolean isSearchFieldVisible() {
        try {
            // Verwendet den SEARCH_INPUT Locator aus HomePageLocators
            waitForElementVisible(driver, SEARCH_INPUT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Sucht nach einem Produkt über das Suchfeld im Header und gibt eine neue Instanz der SearchResultsPage.
    public SearchResultsPage searchForProduct(String productName) {
        waitForElementVisible(driver, SEARCH_INPUT).sendKeys(productName);
        waitForElementClickable(driver, SEARCH_BUTTON).click();
        return new SearchResultsPage(driver);
    }

    // Prüft, ob der Benutzer eingeloggt ist, indem der Account-Link im Header geprüft wird.
    // Wenn der Account-Link sichtbar ist, gibt er true aus (was auf einen Login hindeutet), sonst false.
    public boolean isUserLoggedIn() {
        try {
            return waitForElementVisible(driver, LOGGED_IN_ACCOUNT_LINK).isDisplayed();
        } catch (Exception e) {
            return false; // Element nicht gefunden/sichtbar, also nicht eingeloggt
        }
    }
}