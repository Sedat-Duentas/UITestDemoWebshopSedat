package pages;

import static locators.OrderBillingAddressPageLocators.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static utils.WaitUtils.waitForElementVisible;
import static utils.WaitUtils.waitForElementClickable;

/**
 * Page Object für die Adresseingabe der Bestellungsseite - Schritt 1: Rechnungsadresse.
 */

public class OrderBillingAddressPage {
    private final WebDriver driver;

    public OrderBillingAddressPage(WebDriver driver) {
        this.driver = driver;
    }

    // Füllt die Felder für die Rechnungsadresse aus.
    public void fillBillingAddress(String country, String city, String address, String zip, String phone) {
        waitForElementVisible(driver, COUNTRY_DROPDOWN);
        Select countrySelect = new Select(driver.findElement(COUNTRY_DROPDOWN));
        countrySelect.selectByVisibleText(country);

        waitForElementVisible(driver, CITY_INPUT).clear();
        driver.findElement(CITY_INPUT).sendKeys(city);

        waitForElementVisible(driver, ADDRESS_INPUT).clear();
        driver.findElement(ADDRESS_INPUT).sendKeys(address);

        waitForElementVisible(driver, ZIP_INPUT).clear();
        driver.findElement(ZIP_INPUT).sendKeys(zip);

        waitForElementVisible(driver, PHONE_INPUT).clear();
        driver.findElement(PHONE_INPUT).sendKeys(phone);
    }

    // Klickt auf den "Continue"-Button, um zum nächsten Schritt (Versandadresse) zu gelangen und gibt eine neue Instanz von OrderShippingAddressPage.
    public OrderShippingAddressPage continueToShipping() {
        waitForElementClickable(driver, CONTINUE_BUTTON).click();
        return new OrderShippingAddressPage(driver);
    }
}