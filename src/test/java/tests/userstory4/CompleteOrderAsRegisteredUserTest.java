package tests.userstory4;

import base.TestSetup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.TestUser;

import static org.junit.jupiter.api.Assertions.*;
import static utils.WaitUtils.waitForElementVisible; // <-- DIESEN IMPORT HINZUFÜGEN
import org.openqa.selenium.By; // Dies ist für By.className in WaitUtils.waitForElementVisible notwendig

/**
 * Testfall für User Story 4:
 * Als registrierter Benutzer möchte ich ein Produkt in den Warenkorb legen, mich einloggen und die Bestellung abschließen können damit ich den Kaufvorgang durchführe.
 * Akzeptanzkriterien:
 * - Nach Login bleibt der Warenkorb erhalten.
 * - Checkout erfordert gültige Versand- und Zahlungsdaten.
 * - Nach Abschluss erscheint eine Bestellbestätigung mit Order-Nummer.
 */
public class CompleteOrderAsRegisteredUserTest extends TestSetup {

    @Test
    public void registeredUserCanCompleteOrderSuccessfully() {
        WebDriver driver = this.driver;

        // Arrange (Einrichtung der benötigten Page Objects und Testdaten)
        TestUser user = new TestUser(); // Einmalige Nutzerinstanz mit eindeutiger Email

        // Initialisiere die "Start-Pages"
        HomePage homePage = new HomePage(driver);
        // Deklariere alle Page Objects, die in der Kette zurückgegeben werden
        RegisterPage registerPage;
        LoginPage loginPage;
        CategoryPage categoryPage;
        CartPage cartPage;
        OrderBillingAddressPage billingPage;
        OrderShippingAddressPage shippingPage;
        OrderShippingMethodPage shippingMethodPage;
        OrderPaymentMethodPage paymentMethodPage;
        OrderPaymentInformationPage paymentInfoPage;
        OrderConfirmPage confirmPage;
        OrderCompletedPage completedPage;

        // Act & Assert (Ausführung der Schritte und direkte Überprüfung)

        // 1. Registrierung
        registerPage = homePage.goToRegisterPage();
        registerPage.fillRegistrationForm(user);
        registerPage.clickRegisterButton();
        assertTrue(registerPage.isRegistrationSuccessful(), "Registrierung fehlgeschlagen");
        homePage = registerPage.clickContinueButton();
        assertTrue(homePage.isUserLoggedIn(), "Benutzer ist nach der Registrierung nicht eingeloggt");

        // 2. Ausloggen
        homePage = homePage.logout();

        // 3. Produkt in den Warenkorb legen (als Gast)
        categoryPage = homePage.navigateToCategory("Books");
        categoryPage.addProductToCartByName("Computing and Internet");

        // 4. Login mit registrierten Benutzerdaten
        loginPage = homePage.goToLoginPage();
        homePage = loginPage.login(user.email, user.password);

        // 5. Warenkorb prüfen
        waitForElementVisible(driver, locators.CartPageLocators.CART_QUANTITY);
        cartPage = homePage.goToCart();
        assertTrue(cartPage.isProductInCart("Computing and Internet"), "Produkt nicht im Warenkorb gefunden");
        assertEquals(1, cartPage.getCartQuantityFromHeader(), "Warenkorbanzahl stimmt nicht");

        // 6. Checkout-Prozess starten und durchlaufen

        // 6.1 Warenkorb zum Checkout
        billingPage = cartPage.goToCheckout();

        // 6.2 Rechnungsadresse ausfüllen und weiter zur Versandadresse
        billingPage.fillBillingAddress(user.country, user.city, user.address, user.zipCode, user.phoneNumber);
        shippingPage = billingPage.continueToShipping();

        // 6.3 Versandadresse bestätigen (falls nötig, nur auf 'Continue' drücken)
        shippingMethodPage = shippingPage.continueToShippingMethod();

        // 6.4 Versandmethode auswählen und weiter zur Zahlungsmethode
        shippingMethodPage.selectShippingMethod(1);
        paymentMethodPage = shippingMethodPage.continueToPaymentMethod();

        // 6.5 Zahlungsmethode auswählen und weiter zu Zahlungsinformationen
        paymentMethodPage.selectPaymentMethod(0);
        paymentInfoPage = paymentMethodPage.continueToPaymentInfo();

        // 6.6 Zahlungsinformationen ausfüllen (für "Cash on Delivery" oft nur Weiter)
        confirmPage = paymentInfoPage.continueToConfirmOrder();

        // 6.7 Bestellung bestätigen
        completedPage = confirmPage.confirmOrder();

        // 7. Bestellung abschließen (Bestätigungsseite prüfen)
        assertTrue(completedPage.isOrderCompleted(), "Bestellung wurde nicht erfolgreich abgeschlossen.");

        String orderNumber = completedPage.getOrderNumber();
        System.out.println("Bestellung erfolgreich mit Bestellnummer: " + orderNumber);

        assertNotNull(orderNumber, "Bestellnummer sollte nicht null sein");
        assertFalse(orderNumber.isEmpty(), "Bestellnummer sollte nicht leer sein");
    }
}