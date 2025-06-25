package locators;

import org.openqa.selenium.By;

/**
 * Enthält Locators für die Bestellabschlussseite.
 */
public class OrderCompletedPageLocators {
    public static final By SUCCESS_MESSAGE = By.cssSelector("div[class='title'] strong");
    public static final By ORDER_NUMBER = By.cssSelector("div[class='master-wrapper-main'] li:nth-child(1)");

    public static final By CONTINUE_BUTTON = By.cssSelector("input[value='Continue']");
}