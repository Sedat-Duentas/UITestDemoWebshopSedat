package utils; // Paket für Hilfsklassen

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; // WebDriver zur Browsersteuerung
import org.openqa.selenium.WebElement; // WebElement zur Interaktion mit HTML-Elementen
import org.openqa.selenium.support.ui.ExpectedConditions; // Bedingungen für Wartevorgänge
import org.openqa.selenium.support.ui.WebDriverWait; // Explizite Warte-Logik mit Timeout

import java.time.Duration; // Für Timeout-Angabe in Sekunden

/**
 * Hilfsklasse für explizite Warteoperationen im Umgang mit Selenium WebDriver.
 * Diese Klasse kapselt wiederverwendbare Wait-Logik für häufige Fälle wie Sichtbarkeit oder Klickbarkeit von Elementen.
 */

public class WaitUtils {

    // Standard-Timeout in Sekunden für alle Warteoperationen
    private static final int DEFAULT_TIMEOUT = 15;

    // Wartet, bis ein Web-Element anhand des übergebenen Locators sichtbar ist
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wartet, bis ein Web-Element anhand des übergebenen Locators anklickbar ist
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
