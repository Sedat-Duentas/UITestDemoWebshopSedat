package tests.userstory1;

import base.TestSetup;
import org.junit.jupiter.api.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testfall für die User Story 1:
 * Als Besucher der Website möchte ich die Produktkategorien durchsuchen und mir Details zu Produkten ansehen können damit ich Informationen zu Produkten bekomme, bevor ich mich entscheide.
 * Akzeptanzkriterien:
 * - Kategorien (Books, Computers, Electronics etc.) sind über das Menü erreichbar.
 * - Ein Klick auf ein Produkt öffnet eine Detailseite mit Bild, Beschreibung und Preis.
 * - Die Produktdetailseite enthält eine „Add to cart“-Option
 */
public class CheckDetailsTest extends TestSetup {

    @Test
    void testProductDetailsAreVisible() {

        // Arrange
        HomePage homePage = new HomePage(driver); // Startpunkt

        // Act
        CategoryPage categoryPage = homePage.navigateToCategory("Books"); // HomePage gibt CategoryPage zurück
        ProductPage productPage = categoryPage.openProductDetails("Computing and Internet"); // CategoryPage gibt ProductPage zurück

        // Assert
        assertTrue(productPage.isProductImageVisible(), "Produktbild fehlt auf der Detailseite.");
        assertTrue(productPage.isProductTitleVisible(), "Produkttitel fehlt auf der Detailseite.");
        assertTrue(productPage.isProductDescriptionVisible(), "Produktbeschreibung fehlt auf der Detailseite.");
        assertTrue(productPage.isProductPriceVisible(), "Produktpreis fehlt auf der Detailseite.");
        assertTrue(productPage.isAddToCartButtonVisible(), "'Add to cart'-Button fehlt auf der Detailseite.");
    }
}