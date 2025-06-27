package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Basisklasse für alle UI-Tests. Kapselt die Browserinitialisierung und -beendigung.
 * Konfiguriert den WebDriver dynamisch für lokale Ausführung (ChromeDriver) oder CI/CD (RemoteWebDriver).
 */
public class TestSetup {

    protected WebDriver driver;
    public static final String BASE_URL = "https://demowebshop.tricentis.com/";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Diese Argumente werden immer für beide Umgebungen benötigt
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--incognito"); // Optional, kann hier bleiben

        String seleniumRemoteIp = System.getenv("SELENIUM_REMOTE_IP");
        String seleniumPort = System.getenv("SELENIUM_PORT");

        if (seleniumRemoteIp != null && !seleniumRemoteIp.isEmpty()) {
            // CI/CD-Pipeline-Ausführung: Nutze RemoteWebDriver
            // HIER fügen wir das --headless Argument explizit HINZU
            options.addArguments("--headless"); // HIER IST DAS HEADLESS-ARGUMENT FÜR CI/CD

            String seleniumGridUrl;
            if (seleniumRemoteIp.contains(":")) {
                seleniumGridUrl = "http://[" + seleniumRemoteIp + "]:" + seleniumPort + "/wd/hub";
            } else {
                seleniumGridUrl = "http://" + seleniumRemoteIp + ":" + seleniumPort + "/wd/hub";
            }

            System.out.println("Connecting to remote Selenium Grid at: " + seleniumGridUrl);
            try {
                driver = new RemoteWebDriver(new URL(seleniumGridUrl), options);
            } catch (MalformedURLException e) {
                System.err.println("Fehler bei der Konstruktion der Selenium URL: " + seleniumGridUrl);
                throw new RuntimeException("Ungültige Selenium Remote URL", e);
            } catch (Exception e) {
                System.err.println("Fehler beim Starten des Remote WebDriver von " + seleniumGridUrl + ": " + e.getMessage());
                throw new RuntimeException("Verbindung zum Selenium Grid fehlgeschlagen", e);
            }
        } else {
            // Lokale Ausführung: Nutze WebDriverManager und lokalen ChromeDriver
            System.out.println("SELENIUM_REMOTE_IP not set. Using local ChromeDriver via WebDriverManager.");
            WebDriverManager.chromedriver().setup();

            // HIER wird KEIN --headless Argument HINZUGEFÜGT.
            // Der Browser wird also standardmäßig sichtbar sein, wenn er lokal gestartet wird.

            driver = new ChromeDriver(options); // Lokale Browserinstanz starten
        }

        driver.manage().window().maximize(); // Fenster maximieren
        driver.get(BASE_URL); // Zielseite (URL) laden
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Browser schließen
        }
    }
}