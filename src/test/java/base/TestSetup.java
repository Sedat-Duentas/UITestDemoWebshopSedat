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

    // Der WebDriver für die Browser-Steuerung.
    protected WebDriver driver;
    // Basis-URL der Testumgebung
    public static final String BASE_URL = "https://demowebshop.tricentis.com/";

    @BeforeEach // Methode wird vor jedem Test ausgeführt
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions(); // Erstellt Browser-Optionen
        // Allgemeine Argumente für Chrome/Chromium
        options.addArguments("--disable-gpu");              // Deaktiviert GPU-Nutzung (gut für CI/Headless)
        options.addArguments("--no-sandbox");               // Wichtig für Docker/CI-Umgebungen
        options.addArguments("--disable-dev-shm-usage");    // Wichtig für Docker/CI
        options.addArguments("--incognito");                // Startet den Browser im Inkognito-Modus

        // Umgebungsvariablen für Selenium Grid (aus CI/CD-Pipeline) abrufen
        String seleniumRemoteIp = System.getenv("SELENIUM_REMOTE_IP");
        String seleniumPort = System.getenv("SELENIUM_PORT");

        // Entscheidet, ob Tests lokal oder in CI/CD ausgeführt werden. Wenn SELENIUM_REMOTE_IP gesetzt ist, wird RemoteWebDriver verwendet.
        if (seleniumRemoteIp != null && !seleniumRemoteIp.isEmpty()) {

            // CI/CD-Umgebung: Konfiguriert und initialisiert den RemoteWebDriver für die CI/CD-Umgebung, wobei der Browser headless gestartet und über die aufgebaute URL mit dem Selenium Grid verbunden wird.
            options.addArguments("--headless");
            String seleniumGridUrl = "http://" + seleniumRemoteIp + ":" + seleniumPort + "/wd/hub";
            driver = new RemoteWebDriver(new URL(seleniumGridUrl), options);

        } else { // Lokale Ausführung: Lokal wird der ChromeDriver über WebDriverManager eingerichtet und eine Browserinstanz mit den definierten Optionen gestartet.
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach // Methode wird nach jedem Test ausgeführt
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}