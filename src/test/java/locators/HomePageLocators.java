package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle statischen und dynamischen Locator-Definitionen für die Startseite.
 */
public class HomePageLocators {

    public static final By REGISTER_LINK = By.className("ico-register");
    public static final By LOGIN_LINK = By.className("ico-login");
    public static final By CART_LINK = By.id("topcartlink");
    public static final By SEARCH_INPUT = By.id("small-searchterms");
    public static final By SEARCH_BUTTON = By.cssSelector("input.button-1.search-box-button");
    public static final By LOGOUT_LINK = By.className("ico-logout");
    public static final By LOGGED_IN_ACCOUNT_LINK = By.cssSelector(".header-links .account");
    public static final By BAR_NOTIFICATION_SUCCESS = By.id("bar-notification"); // Oder By.cssSelector(".bar-notification.success")

    // Dynamischer Locator für Kategorien im Hauptmenü
    public static By categoryLink(String categoryName) {
        return By.linkText(categoryName);
    }
}