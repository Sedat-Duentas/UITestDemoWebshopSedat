package locators;

import org.openqa.selenium.By;

/**
 * Enthält alle Locators für die Rechnungsadresse in der Bestellung.
 */

public class OrderBillingAddressPageLocators {
    public static final By COUNTRY_DROPDOWN = By.id("BillingNewAddress_CountryId");
    public static final By CITY_INPUT = By.id("BillingNewAddress_City");
    public static final By ADDRESS_INPUT = By.id("BillingNewAddress_Address1");
    public static final By ZIP_INPUT = By.id("BillingNewAddress_ZipPostalCode");
    public static final By PHONE_INPUT = By.id("BillingNewAddress_PhoneNumber");


    public static final By CONTINUE_BUTTON = By.cssSelector("#billing-buttons-container .new-address-next-step-button");
}