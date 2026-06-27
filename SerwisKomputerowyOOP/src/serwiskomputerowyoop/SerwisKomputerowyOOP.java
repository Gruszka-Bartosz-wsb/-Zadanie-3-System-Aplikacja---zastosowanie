package serwiskomputerowyoop;

import java.util.ArrayList;
import java.util.List;

public class SerwisKomputerowyOOP {
    public static void main(String[] args) {

        Client client1 = new Client(1, "Jan", "Kowalski", "500600700");
        Client client2 = new Client(2, "Anna", "Nowak", "600700800");

        Technician technician1 = new Technician(1, "Marek", "Laptopy");
        Technician technician2 = new Technician(2, "Piotr", "Komputery stacjonarne");

        Device laptop = new Laptop("LAP123", "Lenovo", "ThinkPad", true);
        Device desktop = new DesktopComputer("PC456", "Dell", "Optiplex", true);

        ServiceRequest request1 = new ServiceRequest(
                101,
                client1,
                laptop,
                technician1,
                "Laptop nie laduje baterii"
        );

        ServiceRequest request2 = new ServiceRequest(
                102,
                client2,
                desktop,
                technician2,
                "Komputer dziala wolno i wymaga czyszczenia"
        );

        client1.addRequest(request1);
        client2.addRequest(request2);

        ServiceManager manager = new ServiceManager();
        manager.addRequest(request1);
        manager.addRequest(request2);

        System.out.println("=== LISTA ZGLOSZEN SERWISOWYCH ===");
        manager.showAllRequests();

        System.out.println();
        System.out.println("=== ZMIANA STATUSU ZGLOSZENIA ===");
        request1.changeStatus("W trakcie naprawy");
        request1.showRequestInfo();

        System.out.println();
        System.out.println("=== POLIMORFIZM - ROZNE SPRZETY ===");

        List<Device> devices = new ArrayList<>();
        devices.add(laptop);
        devices.add(desktop);

        for (Device device : devices) {
            System.out.println(device.getDeviceType() + ": " + device.calculateRepairCost() + " zl");
        }

        System.out.println();
        System.out.println("=== PLATNOSCI ===");

        Payment cashPayment = new CashPayment();
        Payment cardPayment = new CardPayment();

        cashPayment.pay(request1.calculateFinalCost());
        cardPayment.pay(request2.calculateFinalCost());
    }
}

class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<ServiceRequest> requests;

    public Client(int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.requests = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addRequest(ServiceRequest request) {
        requests.add(request);
    }

    public void showClientInfo() {
        System.out.println("Klient: " + getFullName());
        System.out.println("Telefon: " + phoneNumber);
        System.out.println("Liczba zgloszen: " + requests.size());
    }
}

class Technician {
    private int id;
    private String name;
    private String specialization;

    public Technician(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void showTechnicianInfo() {
        System.out.println("Technik: " + name);
        System.out.println("Specjalizacja: " + specialization);
    }
}

abstract class Device {
    protected String serialNumber;
    protected String brand;
    protected String model;

    public Device(String serialNumber, String brand, String model) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDeviceName() {
        return brand + " " + model;
    }

    public abstract double calculateRepairCost();

    public abstract String getDeviceType();
}

class Laptop extends Device {
    private boolean batteryProblem;

    public Laptop(String serialNumber, String brand, String model, boolean batteryProblem) {
        super(serialNumber, brand, model);
        this.batteryProblem = batteryProblem;
    }

    @Override
    public double calculateRepairCost() {
        double cost = 150;

        if (batteryProblem) {
            cost += 120;
        }

        return cost;
    }

    @Override
    public String getDeviceType() {
        return "Laptop";
    }

    public boolean hasBatteryProblem() {
        return batteryProblem;
    }
}

class DesktopComputer extends Device {
    private boolean needsCleaning;

    public DesktopComputer(String serialNumber, String brand, String model, boolean needsCleaning) {
        super(serialNumber, brand, model);
        this.needsCleaning = needsCleaning;
    }

    @Override
    public double calculateRepairCost() {
        double cost = 180;

        if (needsCleaning) {
            cost += 80;
        }

        return cost;
    }

    @Override
    public String getDeviceType() {
        return "Komputer stacjonarny";
    }

    public boolean needsCleaning() {
        return needsCleaning;
    }
}

class ServiceRequest {
    private int requestId;
    private Client client;
    private Device device;
    private Technician technician;
    private String problemDescription;
    private String status;

    public ServiceRequest(int requestId, Client client, Device device, Technician technician, String problemDescription) {
        this.requestId = requestId;
        this.client = client;
        this.device = device;
        this.technician = technician;
        this.problemDescription = problemDescription;
        this.status = "Nowe";
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public void changeStatus(String newStatus) {
        if (newStatus != null && !newStatus.isEmpty()) {
            status = newStatus;
        }
    }

    public double calculateFinalCost() {
        return device.calculateRepairCost();
    }

    public void showRequestInfo() {
        System.out.println("Zgloszenie nr: " + requestId);
        System.out.println("Klient: " + client.getFullName());
        System.out.println("Telefon: " + client.getPhoneNumber());
        System.out.println("Sprzet: " + device.getDeviceType() + " - " + device.getDeviceName());
        System.out.println("Numer seryjny: " + device.getSerialNumber());
        System.out.println("Technik: " + technician.getName());
        System.out.println("Opis problemu: " + problemDescription);
        System.out.println("Status: " + status);
        System.out.println("Koszt naprawy: " + calculateFinalCost() + " zl");
    }
}

class ServiceManager {
    private List<ServiceRequest> requests;

    public ServiceManager() {
        this.requests = new ArrayList<>();
    }

    public void addRequest(ServiceRequest request) {
        requests.add(request);
    }

    public void showAllRequests() {
        for (ServiceRequest request : requests) {
            request.showRequestInfo();
            System.out.println("-------------------------");
        }
    }

    public ServiceRequest findRequestById(int id) {
        for (ServiceRequest request : requests) {
            if (request.getRequestId() == id) {
                return request;
            }
        }

        return null;
    }
}

interface Payment {
    void pay(double amount);
}

class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Zaplacono gotowka: " + amount + " zl");
    }
}

class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Zaplacono karta: " + amount + " zl");
    }
}