package utils;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
// import org.junit.jupiter.api.extension.AfterEachCallback; // <-- DIESEN IMPORT ENTFERNEN!
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import base.TestSetup;

import java.util.Optional;

/**
 * JUnit 5 Extension zur automatischen Erstellung von Screenshots bei fehlerhaften Tests.
 * Diese Extension macht KEINEN driver.quit() mehr.
 */
public class AllureScreenshotExtension implements TestWatcher { // AfterEachCallback ENTFERNT

    // Diese Methode wird aufgerufen, wenn ein Test fehlschlägt
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Object> instance = context.getTestInstance();
        if (instance.isPresent() && instance.get() instanceof TestSetup) {
            WebDriver driver = ((TestSetup) instance.get()).getDriver(); // Zugriff auf den Driver über den Getter
            // Nur Screenshot machen, wenn der Driver nicht null und aktiv ist
            // Die NoSuchSessionException, die wir vorher sahen, kommt, wenn driver.quit() zu früh aufgerufen wird.
            // Der einzige Weg, dies zu beheben, wenn driver.quit() im @AfterEach bleibt,
            // ist, den Timeout-Wert des RemoteWebDriver zu erhöhen.
            if (driver != null && driver instanceof TakesScreenshot) {
                try {
                    saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                } catch (Exception e) {
                    System.err.println("Fehler beim Erstellen des Screenshots für Allure: " + e.getMessage());
                }
            }
        }
    }

    // --- WICHTIGE ÄNDERUNG HIER: afterEach() METHODE KOMPLETT ENTFERNEN! ---
    // Sie wird nicht mehr benötigt, da driver.quit() wieder in TestSetup ist.
    // @Override
    // public void afterEach(ExtensionContext context) throws Exception {
    //     Optional<Object> instance = context.getTestInstance();
    //     if (instance.isPresent() && instance.get() instanceof TestSetup) {
    //         WebDriver driver = ((TestSetup) instance.get()).getDriver();
    //         if (driver != null) {
    //             driver.quit();
    //         }
    //     }
    // }
    // --- ENDE WICHTIGER ÄNDERUNG ---

    // Die Methode, die den Screenshot als Anhang für Allure speichert
    @Attachment(value = "Screenshot bei Fehler", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}