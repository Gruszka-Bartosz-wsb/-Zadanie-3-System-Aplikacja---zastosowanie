# Zadanie 3 System / Aplikacja - serwis komputerowy

## Opis projektu

Projekt przedstawia prostą aplikację konsolową napisaną w języku Java. Tematem aplikacji jest obsługa zgłoszeń w serwisie komputerowym.

Program pozwala utworzyć klientów, sprzęty komputerowe, techników oraz zgłoszenia serwisowe. Każde zgłoszenie zawiera klienta, sprzęt, technika, opis problemu, status oraz koszt naprawy.

Projekt został przygotowany w celu pokazania podstawowych elementów programowania obiektowego: klas, obiektów, konstruktorów, właściwości, metod, identyfikatorów, enkapsulacji, dziedziczenia, polimorfizmu, abstrakcji oraz relacji między klasami.

---

## Odpalenie projektu

Aby uruchomić projekt, należy pobrać całe repozytorium z GitHuba jako plik ZIP.

Instrukcja:

1. Wejść w repozytorium projektu na GitHubie.
2. Kliknąć przycisk `Code`.
3. Wybrać opcję `Download ZIP`.
4. Rozpakować pobrany plik ZIP w wybranym miejscu na komputerze.
5. Otworzyć Apache NetBeans.
6. Wybrać `File -> Open Project`.
7. Wskazać folder `SerwisKomputerowyOOP`.
8. Kliknąć `Open Project`.
9. Uruchomić projekt przyciskiem `Run Project` albo klawiszem `F6`.

Projekt jest aplikacją konsolową, więc wynik działania programu pojawi się w oknie Output w NetBeans.

---

## Lista klas

### SerwisKomputerowyOOP

Klasa główna programu. Zawiera metodę `main`, która uruchamia aplikację i tworzy przykładowe dane.

Odpowiedzialność:

* tworzenie przykładowych klientów,
* tworzenie sprzętów,
* tworzenie techników,
* tworzenie zgłoszeń,
* pokazanie działania programu.

---

### Client

Klasa reprezentuje klienta serwisu komputerowego.

Właściwości:

* `id` - identyfikator klienta,
* `firstName` - imię klienta,
* `lastName` - nazwisko klienta,
* `phoneNumber` - numer telefonu,
* `requests` - lista zgłoszeń klienta.

Metody:

* `getId()`,
* `getFullName()`,
* `getPhoneNumber()`,
* `addRequest(ServiceRequest request)`,
* `showClientInfo()`.

Klasa pokazuje relację jeden-do-wielu, ponieważ jeden klient może mieć wiele zgłoszeń serwisowych.

---

### Technician

Klasa reprezentuje technika serwisowego.

Właściwości:

* `id` - identyfikator technika,
* `name` - imię technika,
* `specialization` - specjalizacja technika.

Metody:

* `getId()`,
* `getName()`,
* `getSpecialization()`,
* `showTechnicianInfo()`.

---

### Device

Klasa abstrakcyjna reprezentująca ogólny sprzęt komputerowy.

Właściwości:

* `serialNumber` - numer seryjny sprzętu,
* `brand` - marka,
* `model` - model.

Metody:

* `getSerialNumber()`,
* `getDeviceName()`,
* `calculateRepairCost()`,
* `getDeviceType()`.

Metody `calculateRepairCost()` oraz `getDeviceType()` są abstrakcyjne, ponieważ różne typy sprzętu realizują je inaczej.

---

### Laptop

Klasa reprezentuje laptop. Dziedziczy po klasie `Device`.

Właściwości:

* `batteryProblem` - informacja, czy laptop ma problem z baterią.

Metody:

* `calculateRepairCost()`,
* `getDeviceType()`,
* `hasBatteryProblem()`.

Laptop jest rodzajem sprzętu komputerowego, dlatego dziedziczy po klasie `Device`.

---

### DesktopComputer

Klasa reprezentuje komputer stacjonarny. Dziedziczy po klasie `Device`.

Właściwości:

* `needsCleaning` - informacja, czy komputer wymaga czyszczenia.

Metody:

* `calculateRepairCost()`,
* `getDeviceType()`,
* `needsCleaning()`.

Komputer stacjonarny jest rodzajem sprzętu komputerowego, dlatego dziedziczy po klasie `Device`.

---

### ServiceRequest

Klasa reprezentuje zgłoszenie serwisowe.

Właściwości:

* `requestId` - identyfikator zgłoszenia,
* `client` - klient zgłoszenia,
* `device` - sprzęt oddany do serwisu,
* `technician` - technik przypisany do zgłoszenia,
* `problemDescription` - opis problemu,
* `status` - status zgłoszenia.

Metody:

* `getRequestId()`,
* `getStatus()`,
* `changeStatus(String newStatus)`,
* `calculateFinalCost()`,
* `showRequestInfo()`.

Klasa łączy klienta, sprzęt i technika w jednym zgłoszeniu.

---

### ServiceManager

Klasa odpowiada za zarządzanie zgłoszeniami serwisowymi.

Właściwości:

* `requests` - lista wszystkich zgłoszeń.

Metody:

* `addRequest(ServiceRequest request)`,
* `showAllRequests()`,
* `findRequestById(int id)`.

---

### Payment

Interfejs opisujący wspólne zachowanie płatności.

Metody:

* `pay(double amount)`.

Interfejs określa, że każda forma płatności musi posiadać metodę `pay`.

---

### CashPayment

Klasa reprezentuje płatność gotówką. Implementuje interfejs `Payment`.

Metody:

* `pay(double amount)`.

---

### CardPayment

Klasa reprezentuje płatność kartą. Implementuje interfejs `Payment`.

Metody:

* `pay(double amount)`.

---

## Relacje między klasami

### Client - ServiceRequest

Klient ma wiele zgłoszeń serwisowych.

Relacja została wykonana przez kolekcję:

```java
private List<ServiceRequest> requests;
```

Jest to relacja jeden-do-wielu.

---

### ServiceRequest - Client

Zgłoszenie ma przypisanego klienta:

```java
private Client client;
```

Jest to relacja przez właściwość.

---

### ServiceRequest - Device

Zgłoszenie ma przypisany sprzęt:

```java
private Device device;
```

Dzięki temu zgłoszenie może obsługiwać różne typy sprzętu, np. laptop i komputer stacjonarny.

---

### ServiceRequest - Technician

Zgłoszenie ma przypisanego technika:

```java
private Technician technician;
```

Jest to relacja przez właściwość.

---

### Device - Laptop / DesktopComputer

Klasy `Laptop` i `DesktopComputer` dziedziczą po klasie `Device`:

```java
class Laptop extends Device
class DesktopComputer extends Device
```

Jest to relacja „jest rodzajem”, ponieważ laptop jest sprzętem komputerowym, a komputer stacjonarny także jest sprzętem komputerowym.

---

### Payment - CashPayment / CardPayment

Klasy `CashPayment` i `CardPayment` implementują interfejs `Payment`:

```java
class CashPayment implements Payment
class CardPayment implements Payment
```

Obie klasy mają metodę `pay()`, ale wykonują ją w inny sposób.

---

## Cztery zasady OOP

### Enkapsulacja

Enkapsulacja została zastosowana przez prywatne pola w klasach, np.:

```java
private int requestId;
private String status;
private List<ServiceRequest> requests;
```

Dane nie są zmieniane bezpośrednio z zewnątrz. Status zgłoszenia zmienia się przez metodę:

```java
changeStatus(String newStatus)
```

Dzięki temu klasa sama kontroluje swój stan.

---

### Dziedziczenie

Dziedziczenie występuje w klasach `Laptop` i `DesktopComputer`:

```java
class Laptop extends Device
class DesktopComputer extends Device
```

Obie klasy przejmują wspólne cechy klasy `Device`, takie jak numer seryjny, marka i model.

---

### Polimorfizm

Polimorfizm występuje przy metodzie `calculateRepairCost()`.

Ta sama metoda jest wywoływana na różnych typach sprzętu:

```java
for (Device device : devices) {
    System.out.println(device.calculateRepairCost());
}
```

Laptop i komputer stacjonarny liczą koszt naprawy inaczej, ale są używane przez wspólny typ `Device`.

Polimorfizm występuje także przy płatnościach:

```java
Payment cashPayment = new CashPayment();
Payment cardPayment = new CardPayment();
```

Oba obiekty mają metodę `pay()`, ale wykonują ją inaczej.

---

### Abstrakcja

Abstrakcja została zastosowana przez klasę abstrakcyjną `Device`:

```java
abstract class Device
```

Klasa `Device` określa, że każdy sprzęt musi posiadać metody `calculateRepairCost()` oraz `getDeviceType()`, ale szczegóły działania są zapisane w klasach `Laptop` i `DesktopComputer`.

Abstrakcja została też pokazana przez interfejs `Payment`:

```java
interface Payment
```

Interfejs określa, że każda płatność musi posiadać metodę `pay()`.

---

## Lista kontrolna przed oddaniem

* Projekt ma własny temat i opis: serwis komputerowy.
* W projekcie są klasy, obiekty, konstruktory, właściwości i metody.
* Główne obiekty mają identyfikatory: `Client` ma `id`, `Technician` ma `id`, `ServiceRequest` ma `requestId`, `Device` ma `serialNumber`.
* Enkapsulacja została zastosowana przez pola `private` oraz metody dostępowe.
* Dziedziczenie występuje w klasach `Laptop` i `DesktopComputer`, które dziedziczą po `Device`.
* Polimorfizm występuje przy wywołaniu `calculateRepairCost()` na obiektach typu `Device`.
* Abstrakcja została zastosowana przez klasę abstrakcyjną `Device` oraz interfejs `Payment`.
* Relacje między klasami występują przez właściwości, konstruktory oraz kolekcję `List<ServiceRequest>`.
* Kod można uruchomić jako aplikację konsolową.
* README opisuje, gdzie zastosowano najważniejsze elementy OOP.

---

## Przykładowe działanie programu

Program tworzy przykładowych klientów, techników, sprzęty i zgłoszenia. Następnie wyświetla listę zgłoszeń, zmienia status jednego zgłoszenia, pokazuje polimorfizm na różnych typach sprzętu oraz wykonuje płatność gotówką i kartą.

---
