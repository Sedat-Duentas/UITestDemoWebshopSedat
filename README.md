# UI Testautomatisierung - Tricentis Demo Web Shop (Selenium/Java)

![Build Status](https://gitlab.com/sedat1990-group/uitestdemowebshopsedat.git)
Dieses Projekt demonstriert die automatisierte UI-Testautomatisierung für den **Tricentis Demo Web Shop** (`https://demowebshop.tricentis.com/`). Es verwendet Java, Maven und eine Best-Practice-Struktur, um robuste, wartbare und skalierbare UI-Tests zu erstellen und in einer CI/CD-Pipeline auszuführen.

### **1. Projektübersicht**

Das Hauptziel dieses Projekts ist es, wichtige Benutzerinteraktionen und Funktionalitäten des Tricentis Demo Web Shops über die Benutzeroberfläche (UI) hinweg automatisiert zu testen. Dies gewährleistet, dass kritische Workflows für den Endbenutzer wie erwartet funktionieren und regressionsfrei sind. Die Tests sind nach User Stories organisiert und decken wesentliche Aspekte eines E-Commerce-Systems ab.

### **2. Verwendete Technologien**

* **Java:** Die Programmiersprache, in der die Testautomatisierungsskripte geschrieben sind.
* **Maven:** Ein leistungsstarkes Build-Automatisierungstool, das die Abhängigkeitsverwaltung, das Kompilieren und die Testausführung des Projekts steuert.
* **Selenium WebDriver:** Das führende Framework für die Browser-Automatisierung, das die Interaktion mit den Web-Elementen ermöglicht (Klicks, Eingaben, Validierungen).
* **JUnit 5:** Ein beliebtes Test-Framework für Java, das die Strukturierung und Ausführung der Testfälle ermöglicht.
* **Page Object Model (POM):** Ein Design-Pattern, das für UI-Automatisierungsprojekte verwendet wird, um Web-Elemente und deren Interaktionen von den Testlogik-Dateien zu trennen. Dies erhöht die Wartbarkeit und Lesbarkeit der Tests.
* **GitLab CI/CD:** Eine Continuous Integration / Continuous Delivery Plattform, die die automatische Ausführung der Tests bei jeder Code-Änderung ermöglicht.

### **3. Projektstruktur**

Das Projekt folgt einer klaren und bewährten Struktur, die die Best Practices für testautomatisierte Projekte widerspiegelt:

![Projektstruktur]("C:\Users\Sedat Düntas\Desktop\Taskforce\UI-Test\JUnit\Projektstruktur.png")


### **4. Automatisierte Testfälle und Szenarien**

Die Testfälle sind nach User Stories in separaten Paketen innerhalb des `tests/` Verzeichnisses organisiert. Jeder Testfall implementiert eine spezifische Funktionalität des Demo Web Shops und validiert deren Verhalten über die Benutzeroberfläche.

1.  **User Story 1: Produktdetails durchsuchen (`CheckDetailsTest.java`)**
    * **Ziel:** Als Besucher die Produktkategorien durchsuchen und Details zu Produkten einsehen können.
    * **Szenario:** Navigiert zur Kategorie "Books", öffnet die Detailseite für "Computing and Internet" und verifiziert, dass Produktbild, -titel, -beschreibung, -preis und der "Add to cart"-Button sichtbar sind.

2.  **User Story 2: Produkte in den Warenkorb legen (als Gast) (`CheckAddToCartAsGuestTest.java`)**
    * **Ziel:** Als Gast (ohne Login) Produkte in den Warenkorb legen können.
    * **Szenario:** Fügt ein Produkt direkt aus der Kategorieansicht ("Computing and Internet" aus "Books") hinzu. Fügt anschließend zwei weitere Produkte ("Blue Jeans", "Casual Golf Belt" aus "Apparel & Shoes") über deren Detailseiten hinzu. Überprüft die korrekte Aktualisierung der Warenkorbmenge im Header und die Präsenz aller Produkte im Warenkorb.

3.  **User Story 3: Benutzerkonto erstellen (`CheckRegisterTest.java`)**
    * **Ziel:** Als neuer Benutzer erfolgreich ein Benutzerkonto erstellen können.
    * **Szenario:** Navigiert zur Registrierungsseite, füllt das Registrierungsformular mit eindeutigen Testdaten aus, klickt auf "Register" und verifiziert, dass die Registrierung erfolgreich war und der Benutzer automatisch eingeloggt ist.

4.  **User Story 4: Bestellung als registrierter Benutzer abschließen (`CompleteOrderAsRegisteredUserTest.java`)**
    * **Ziel:** Als registrierter Benutzer ein Produkt bestellen und den gesamten Checkout-Prozess erfolgreich durchlaufen können.
    * **Szenario:** Registriert einen neuen Benutzer, loggt sich dann aus. Legt ein Produkt (als Gast) in den Warenkorb, loggt sich dann mit den registrierten Daten ein (der Warenkorb bleibt erhalten). Durchläuft den vollständigen Checkout-Prozess (Rechnungs-/Versandadresse, Versand-/Zahlungsmethode, Bestätigung) und verifiziert die Bestellbestätigung inklusive einer Bestellnummer.

5.  **User Story 5: Produktsuchfunktion (`SearchFunctionTest.java`)**
    * **Ziel:** Als Benutzer gezielt nach Produkten über das Suchfeld suchen und relevante Ergebnisse finden können.
    * **Szenario:** Überprüft die Sichtbarkeit des Suchfeldes im Header. Führt eine Suche nach dem Begriff "diamond" durch und validiert, dass ein spezifisches Produkt ("Black & White Diamond Heart") in den Ergebnissen gefunden wird und dass die Ergebnisse nach Relevanz sortiert sind.

Jeder Testfall ist so konzipiert, dass er isoliert ausgeführt werden kann, aber auch als Teil einer umfassenden Regressionstest-Suite dient.

### **5. Wie man das Projekt ausführt**

#### **Lokale Ausführung und CI/CD-Integration**

Dieses UI-Testprojekt ist darauf ausgelegt, flexibel ausgeführt zu werden:

* **Lokal:** Testfälle können jederzeit als einzelne Tests direkt in Ihrer IDE (z.B. IntelliJ IDEA) gestartet oder alternativ über Maven im Terminal ausgeführt werden (`mvn test` oder `mvn clean install`).
* **CI/CD-Integration:** Das Projekt ist mit einer `.gitlab-ci.yml`-Datei konfiguriert und vollständig in eine **GitLab CI/CD Pipeline** integriert. Jede Änderung am Code, die in das Repository gepusht wird, löst automatisch diese Pipeline aus. Die Testergebnisse sind nach Abschluss der Pipeline in GitLab unter dem Abschnitt "CI/CD" -> "Pipelines" einsehbar.

### **6. Ergebnisse und Schlussfolgerung**

Dieses Projekt bietet eine robuste Grundlage für die UI-Testautomatisierung von Webanwendungen. Durch die konsequente Anwendung von Design-Patterns wie dem Page Object Model und die Integration in eine CI/CD-Pipeline wird die Qualität der Software kontinuierlich sichergestellt und die Regressionstests automatisiert. Die erfolgreiche Ausführung der Tests bestätigt die Funktionalität der getesteten UI-Workflows des Demo Web Shops.

---