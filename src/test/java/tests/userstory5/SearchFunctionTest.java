package tests.userstory5;

import base.TestSetup;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testfall für User Story 5:
 * Als Benutzer möchte ich gezielt nach Produkten über das Suchfeld suchen können damit ich schnell relevante Ergebnisse finde.
 * Akzeptanzkriterien:
 * - Das Suchfeld ist jederzeit im Header sichtbar.
 * - Die Suche liefert relevante Treffer (z.B. Eingabe „laptop“ zeigt passende Produkte).
 * - Die Ergebnisseite ist nach Relevanz sortiert.
 */

public class SearchFunctionTest extends TestSetup {

    @Test
    void userCanSearchForProductsSuccessfully() {
        // Arrange
        HomePage homePage = new HomePage(driver);
        String searchTerm = "diamond"; // Definiert den Suchbegriff, den wir im Test verwenden werden.
        String expectedProduct = "Black & White Diamond Heart"; // Das spezifische Produkt, das wir in den Suchergebnissen erwarten.
        //String expectedProduct = "NonExistent Diamond Product"; // Das spezifische Produkt, das wir NICHT in den Suchergebnissen erwarten.
        //Test

        // Die 'expectedSortedProducts'-Liste definiert die exakte Reihenfolge der Produkttitel, die wir als "relevant" betrachten und die am Anfang der Suchergebnisse erscheinen sollten.
        List<String> expectedSortedProducts = Arrays.asList(
                "Black & White Diamond Heart", // Dieses Produkt sollte das erste und relevanteste Ergebnis sein
                "Diamond Pave Earrings",
                "Diamond Tennis Bracelet",
                "Vintage Style Three Stone Diamond Engagement Ring"
        );

        // --- Act & Assert (Phase 1: Suchfeld-Sichtbarkeit prüfen vor der Interaktion) ---

        // Prüft, ob das Suchfeld vor der Benutzung sichtbar ist.
        assertTrue(homePage.isSearchFieldVisible(), "Das Suchfeld sollte im Header sichtbar sein.");

        // --- Act (Phase 2: Suche ausführen) ---

        // Führe die Suche aus und navigiere zur Ergebnisseite.
        SearchResultsPage searchResultsPage = homePage.searchForProduct(searchTerm);

        // --- Assert (Phase 2 & 3: Relevante Treffer und Sortierung prüfen) ---

        // Prüft, ob das spezifische Produkt in den Ergebnissen vorhanden ist.
        assertTrue(searchResultsPage.isProductFound(expectedProduct),
                "Produkt '" + expectedProduct + "' sollte in den Suchergebnissen für '" + searchTerm + "' sein.");

        // Prüft, ob die tatsächlichen Ergebnisse mit der erwarteten, relevanten Reihenfolge übereinstimmen.
        assertTrue(searchResultsPage.areProductsSortedByRelevance(expectedSortedProducts),
                "Die Suchergebnisse sollten nach Relevanz sortiert sein.");
    }
}