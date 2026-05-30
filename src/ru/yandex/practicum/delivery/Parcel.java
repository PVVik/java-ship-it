package ru.yandex.practicum.delivery;

import java.util.Objects;

public abstract class Parcel {

    private final String description;

    private final int weight;

    private final String deliveryAddress;

    private final int sendDay;

    public static final int PRICE_FOR_STANDARD = 2;

    public static final int PRICE_FOR_FRAGILE = 3;

    public static final int PRICE_FOR_PERISHABLE = 4;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " упакована.");
    }

    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + getDeliveryAddress());
    }

    public abstract int calculateDeliveryCost();

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Double.compare(weight, parcel.weight) == 0 && sendDay == parcel.sendDay && Objects.equals(description, parcel.description) && Objects.equals(deliveryAddress, parcel.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, deliveryAddress, sendDay);
    }
}
