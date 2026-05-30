package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {

    private final List<T> inBox;

    private final int maxWeight;

    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.inBox = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Коробка полная, добавить посылку невозможно!");
            return;
        }
        inBox.add(parcel);
        currentWeight += parcel.getWeight();
    }

    public void getAllParcels() {
        for (T parcel : inBox) {
            System.out.println(parcel.getDescription());
        }
    }

    public List<T> getInBox() {
        return inBox;
    }
}
