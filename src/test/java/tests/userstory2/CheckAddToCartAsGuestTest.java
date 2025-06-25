package tests.userstory2;

import base.TestSetup;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;
import utils.WaitUtils; // Beibehalten, da es in this.driver.get() und .isProductInCart() verwendet wird

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By; // Für By.className in WaitUtils.waitForElementVisible

/**
 * Testklasse für die User Story 2: Produkte in den Warenkorb legen (ohne Login).
 * Prüft das Hinzufügen von Produkten aus der Kategorieansicht und von der Detailseite
 * sowie die korrekte Aktualisierung des Warenkorbs.
 */
public class CheckAddToCartAsGuestTest extends TestSetup {

    @Test
    public void testAddProductToCartAsGuest() {
        // Arrange
        HomePage homePage = new HomePage(driver);
        CategoryPage categoryPage;
        ProductPage productPage;
        CartPage cartPage;

        // --- 1. Produkt aus "Books" direkt aus der Kategorieansicht hinzufügen ---
        // Act
        categoryPage = homePage.navigateToCategory("Books"); // HomePage gibt CategoryPage zurück
        categoryPage.addProductToCartByName("Computing and Internet"); // void
        cartPage = homePage.goToCart(); // HomePage gibt CartPage zurück
        // Assert
        WaitUtils.waitForElementVisible(driver, By.className("cart-qty")); // Warten auf Header-Update
        assertEquals(1, cartPage.getCartQuantityFromHeader(), "Header-Anzahl sollte 1 sein nach Produkt 1 (Kategorie-Seite).");

        // --- 2. Produkt "Blue Jeans" über Detailseite hinzufügen ---
        // Act
        homePage = homePage.navigateToHomePage(); // << WICHTIG: Zurück zur Homepage, bevor neue Kategorie gewählt wird
        categoryPage = homePage.navigateToCategory("Apparel & Shoes"); // HomePage gibt CategoryPage zurück
        productPage = categoryPage.openProductDetails("Blue Jeans"); // CategoryPage gibt ProductPage zurück
        productPage.addToCart(); // void
        cartPage = homePage.goToCart(); // HomePage gibt CartPage zurück
        // Assert
        WaitUtils.waitForElementVisible(driver, By.className("cart-qty"));
        assertEquals(2, cartPage.getCartQuantityFromHeader(), "Header-Anzahl sollte 2 sein nach Produkt 2 (Detailseite).");

        // --- 3. Produkt "Casual Golf Belt" über Detailseite hinzufügen ---
        // Act
        homePage = homePage.navigateToHomePage(); // << WICHTIG: Zurück zur Homepage, bevor neue Kategorie gewählt wird
        categoryPage = homePage.navigateToCategory("Apparel & Shoes"); // HomePage gibt CategoryPage zurück
        productPage = categoryPage.openProductDetails("Casual Golf Belt"); // CategoryPage gibt ProductPage zurück
        productPage.addToCart(); // void
        cartPage = homePage.goToCart(); // HomePage gibt CartPage zurück
        // Assert
        WaitUtils.waitForElementVisible(driver, By.className("cart-qty"));
        assertEquals(3, cartPage.getCartQuantityFromHeader(), "Header-Anzahl sollte 3 sein nach Produkt 3 (Detailseite).");

        // --- Abschließende Prüfung des Warenkorb-Inhalts ---
        // Assert
        assertTrue(cartPage.isProductInCart("Computing and Internet"), "Produkt 'Computing and Internet' sollte im Warenkorb sein.");
        assertTrue(cartPage.isProductInCart("Blue Jeans"), "Produkt 'Blue Jeans' sollte im Warenkorb sein.");
        assertTrue(cartPage.isProductInCart("Casual Golf Belt"), "Produkt 'Casual Golf Belt' sollte im Warenkorb sein.");
    }
}