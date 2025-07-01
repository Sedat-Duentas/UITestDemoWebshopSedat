package utils;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import base.TestSetup;

import java.util.Optional;

/**
 * JUnit 5 Extension zur automatischen Erstellung von Screenshots bei fehlerhaften Tests.
 */
public class AllureScreenshotExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Object> instance = context.getTestInstance();
        if (instance.isPresent() && instance.get() instanceof TestSetup) {
            WebDriver driver = ((TestSetup) instance.get()).getDriver();
            if (driver instanceof TakesScreenshot) {
                saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            }
        }
    }

    @Attachment(value = "Screenshot bei Fehler", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}