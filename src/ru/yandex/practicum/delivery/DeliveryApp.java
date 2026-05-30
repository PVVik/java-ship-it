package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackables = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardParcelParcelBox = new ParcelBox<>(500);
    private static ParcelBox<FragileParcel> fragileParcelParcelBox = new ParcelBox<>(200);
    private static ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(300);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    report();
                    break;
                case 5:
                    showParcelBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - Отследить посылку");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите описание");
        String description = scanner.nextLine();

        System.out.println("Введите вес");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите адрес доставки");
        String address = scanner.nextLine();

        System.out.println("В какой день отправить посылку?");
        int day = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                Parcel standardParcel = new StandardParcel(description, weight, address, day);
                allParcels.add(standardParcel);
                standardParcelParcelBox.addParcel((StandardParcel) standardParcel);
                break;
            case 2:
                Parcel fragileParcel = new FragileParcel(description, weight, address, day);
                allParcels.add(fragileParcel);
                trackables.add((Trackable) fragileParcel);
                fragileParcelParcelBox.addParcel((FragileParcel) fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок хранения");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                Parcel perishableParcel = new PerishableParcel(description, weight, address, day, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcelParcelBox.addParcel((PerishableParcel) perishableParcel);
                break;
            default:
                System.out.println("Выбран неверный тип посылки");
        }

        System.out.println("Посылка добавлена");
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int sum = 0;

        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }

        System.out.println("Общая сумма доставки составляет " + sum);
    }

    private static void report() {
        System.out.println("Введите пункт назначения");
        String newLocation = scanner.nextLine();
        for (Trackable trackable : trackables) {
            trackable.reportStatus(newLocation);
        }
    }

    private static void showParcelBox() {
        System.out.println("Выберите коробку:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        int type = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                standardParcelParcelBox.getAllParcels();
                break;
            case 2:
                fragileParcelParcelBox.getAllParcels();
                break;
            case 3:
                perishableParcelParcelBox.getAllParcels();
                break;
            default:
                System.out.println("Выбран неверный тип коробки");
        }
    }
}

