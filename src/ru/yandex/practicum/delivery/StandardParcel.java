package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);

    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * PRICE_FOR_STANDARD;
    }


}
