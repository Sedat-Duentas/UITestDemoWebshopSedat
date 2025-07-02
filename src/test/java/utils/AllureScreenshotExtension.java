package utils;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.extension.AfterEachCallback; // WICHTIG: Import für AfterEachCallback
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import base.TestSetup;

import java.util.Optional;

/**
 * JUnit 5 Extension zur automatischen Erstellung von Screenshots bei fehlerhaften Tests.
 * Kümmert sich auch um das Schließen des WebDrivers NACH dem Screenshot bei Fehlern.
 */
public class AllureScreenshotExtension implements TestWatcher, AfterEachCallback { // BEIDE INTERFACES IMPLEMENTIEREN

    // Diese Methode wird aufgerufen, wenn ein Test fehlschlägt
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Object> instance = context.getTestInstance();
        if (instance.isPresent() && instance.get() instanceof TestSetup) {
            WebDriver driver = ((TestSetup) instance.get()).getDriver(); // Zugriff auf den Driver über den Getter
            // Nur Screenshot machen, wenn der Driver nicht null und aktiv ist
            if (driver != null && driver instanceof TakesScreenshot) { // Sicherstellen, dass es ein Screenshot-fähiger Driver ist
                try {
                    saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                } catch (Exception e) {
                    System.err.println("Fehler beim Erstellen des Screenshots für Allure: " + e.getMessage());
                }
            }
        }
    }

    // Diese Methode wird nach jedem Test ausgeführt (erfolgreich oder fehlerhaft).
    // Sie wird NACH testFailed() aufgerufen.
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Optional<Object> instance = context.getTestInstance();
        if (instance.isPresent() && instance.get() instanceof TestSetup) {
            WebDriver driver = ((TestSetup) instance.get()).getDriver();
            if (driver != null) {
                driver.quit(); // <-- DER DRIVER WIRD HIER GESCHLOSSEN!
                driver = null; // Den Driver auch hier auf null setzen
            }
        }
    }

    // Die Methode, die den Screenshot als Anhang für Allure speichert
    @Attachment(value = "Screenshot bei Fehler", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}