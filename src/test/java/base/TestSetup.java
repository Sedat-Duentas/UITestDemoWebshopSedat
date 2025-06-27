package base;

import io.github.bonigarcia.wdm.WebDriverManager; // WIEDER AKTIVIEREN FÜR LOKALE NUTZUNG
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;     // WIEDER AKTIVIEREN FÜR LOKALE NUTZUNG
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver; // FÜR CI/CD-NUTZUNG
import java.net.URL;                               // FÜR CI/CD-NUTZUNG
import java.net.MalformedURLException;             // FÜR CI/CD-NUTZUNG

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
        options.addArguments("--headless");         // Standardmäßig headless, kann lokal auskommentiert werden
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");       // Wichtig für Docker/CI
        options.addArguments("--disable-dev-shm-usage"); // Wichtig für Docker/CI
        options.addArguments("--incognito");        // Öffnet Chrome im Inkognito-Modus (optional)

        // --- WICHTIG: KEINE options.setBinary() ZEILE HIER ---
        // options.setBinary("/usr/bin/chromium"); // DIESE ZEILE MUSS KOMPLETT ENTFERNT SEIN ODER AUSKOMMENTIERT

        // Prüfe, ob wir in einer CI/CD-Umgebung sind, indem wir eine spezifische Umgebungsvariable abfragen
        // SELENIUM_REMOTE_IP wird von unserem GitLab CI/CD Skript gesetzt
        String seleniumRemoteIp = System.getenv("SELENIUM_REMOTE_IP"); // NEU: Diese Variable verwenden!
        String seleniumPort = System.getenv("SELENIUM_PORT"); // Port wird weiterhin benötigt

        if (seleniumRemoteIp != null && !seleniumRemoteIp.isEmpty()) {
            // CI/CD-Pipeline-Ausführung: Nutze RemoteWebDriver
            String seleniumGridUrl;
            if (seleniumRemoteIp.contains(":")) { // Prüft, ob es eine IPv6-Adresse ist
                seleniumGridUrl = "http://[" + seleniumRemoteIp + "]:" + seleniumPort + "/wd/hub";
            } else { // Wenn es eine IPv4-Adresse oder ein Hostname ist
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
            WebDriverManager.chromedriver().setup(); // Stellt sicher, dass der passende ChromeDriver geladen ist

            // Optional: Für lokale sichtbare Ausführung den Headless-Modus entfernen
            // options.removeArguments("--headless");

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