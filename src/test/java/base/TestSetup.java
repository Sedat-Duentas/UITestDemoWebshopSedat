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

    // Der WebDriver für die Browser-Steuerung
    protected WebDriver driver;
    // Basis-URL der Testumgebung
    public static final String BASE_URL = "https://demowebshop.tricentis.com/";

    @BeforeEach // Methode wird vor jedem Test ausgeführt
    public void setUp() {
        ChromeOptions options = new ChromeOptions(); // Erstellt Browser-Optionen
        // Allgemeine Argumente für Chrome/Chromium
        options.addArguments("--disable-gpu");              // Deaktiviert GPU-Nutzung (gut für CI/Headless)
        options.addArguments("--no-sandbox");               // Wichtig für Docker/CI-Umgebungen
        options.addArguments("--disable-dev-shm-usage");    // Wichtig für Docker/CI
        options.addArguments("--incognito");                // Startet den Browser im Inkognito-Modus

        // Umgebungsvariablen für Selenium Grid (aus CI/CD-Pipeline) abrufen
        String seleniumRemoteIp = System.getenv("SELENIUM_REMOTE_IP");
        String seleniumPort = System.getenv("SELENIUM_PORT");

        // Entscheidet, ob Tests lokal oder in CI/CD ausgeführt werden
        if (seleniumRemoteIp != null && !seleniumRemoteIp.isEmpty()) {
            // CI/CD-Umgebung: RemoteWebDriver nutzen
            options.addArguments("--headless"); // Fügt Headless-Argument nur für CI hinzu

            // URL für Selenium Grid aufbauen (berücksichtigt IPv6-Adressen)
            String seleniumGridUrl; // Hier wird ein Platzhalter für die Internet-Adresse des Browser-Servers erstellt.
            if (seleniumRemoteIp.contains(":")) { // Prüft auf IPv6
                seleniumGridUrl = "http://[" + seleniumRemoteIp + "]:" + seleniumPort + "/wd/hub";
            } else { // Annahme: IPv4 oder Hostname
                seleniumGridUrl = "http://" + seleniumRemoteIp + ":" + seleniumPort + "/wd/hub";
            }

            System.out.println("Connecting to remote Selenium Grid at: " + seleniumGridUrl);
            // RemoteWebDriver mit der konfigurierten URL initialisieren
            try {
                driver = new RemoteWebDriver(new URL(seleniumGridUrl), options);
            } catch (MalformedURLException e) {
                // Fehler bei ungültiger URL-Syntax
                System.err.println("Fehler bei der Konstruktion der Selenium URL: " + seleniumGridUrl);
                throw new RuntimeException("Ungültige Selenium Remote URL", e);
            } catch (Exception e) {
                // Allgemeine Fehler beim Start des Remote WebDriver
                System.err.println("Fehler beim Starten des Remote WebDriver von " + seleniumGridUrl + ": " + e.getMessage());
                throw new RuntimeException("Verbindung zum Selenium Grid fehlgeschlagen", e);
            }
        } else {
            // Lokale Ausführung: Nutze WebDriverManager und lokalen ChromeDriver
            WebDriverManager.chromedriver().setup(); // Lädt/konfiguriert den ChromeDriver automatisch
            driver = new ChromeDriver(options); // Lokale Browserinstanz starten
        }

        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach // Methode wird nach jedem Test ausgeführt
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Browser schließen
        }
    }
}