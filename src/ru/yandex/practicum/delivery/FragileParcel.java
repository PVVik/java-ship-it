package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{

    public static final int PRICE_FOR_FRAGILE = 3;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem(){
        System.out.println("Посылка " + getDescription() + " обернута в защитную пленку.");
        super.packageItem();
    }

    @Override
    public int getPrice() {
        return PRICE_FOR_FRAGILE;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }
}
