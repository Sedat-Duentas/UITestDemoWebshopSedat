package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Basisklasse für alle UI-Tests. Kapselt die Browserinitialisierung und -beendigung.
 */

public class TestSetup {

    // WebDriver steht den Testklassen zur Verfügung
    protected WebDriver driver;

    // Basis-URL als statische Konstante
    public static final String BASE_URL = "https://demowebshop.tricentis.com/";

    // Wird vor jedem Test aufgerufen. Initialisiert den Chrome-Browser im Inkognito-Modus und öffnet die Zielseite.
    @BeforeEach
    public void setUp() {
        // --- WICHTIGE ÄNDERUNG HIER: WebDriverManager.chromedriver().setup() ENTFERNEN ---
        // WebDriverManager.chromedriver().setup(); // Diese Zeile entfernen oder auskommentieren

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Für CI/CD-Umgebung
        options.addArguments("--disable-gpu"); // Optional: Manchmal hilfreich auf Linux-Systemen
        options.addArguments("--no-sandbox"); // Wichtig für Docker/CI-Umgebungen, um Sandbox-Fehler zu vermeiden
        options.addArguments("--disable-dev-shm-usage"); // Wichtig für Docker/CI-Umgebungen
        options.addArguments("--incognito"); // Öffnet Chrome im Inkognito-Modus (optional in Headless)

        // Füge den Pfad zum Chromium-Binary hinzu, das in der CI-Umgebung installiert wurde
        options.setBinary("/usr/bin/chromium"); // Oder "/usr/bin/chromium-browser" falls das der Pfad ist

        driver = new ChromeDriver(options); // Browserinstanz starten
        driver.manage().window().maximize(); // Fenster maximieren
        driver.get(BASE_URL); // Zielseite (URL) laden
    }

    /*
    // Wird vor jedem Test aufgerufen. Initialisiert den Chrome-Browser im Inkognito-Modus und öffnet die Zielseite.
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Stellt sicher, dass der passende ChromeDriver geladen ist
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // öffnet Chrome im Inkognito-Modus
        driver = new ChromeDriver(options); // Browserinstanz starten
        driver.manage().window().maximize(); // Fenster maximieren
        driver.get(BASE_URL); // Zielseite (URL) laden
    }
     */
    // Wird nach jedem Test aufgerufen. Schließt den Browser und gibt Ressourcen frei.
    @AfterEach
    public void tearDown() {
        if (driver != null) { // Wenn ein Browser existiert
            driver.quit(); // Browser schließen
        }
    }
}
