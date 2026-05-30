package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

public class DeliveryCostTest {

    private final Parcel standardParcel = new StandardParcel("standardParcel", 10,
            "standardAddress", 10);

    private final Parcel fragileParcel = new FragileParcel("fragileParcel", 110,
            "fragileAddress", 11);

    private final PerishableParcel perishableParcel = new PerishableParcel("perishableParcel", 1,
            "perishableAddress", 1, 10);

    private final ParcelBox<StandardParcel> standardParcelParcelBox = new ParcelBox<>(100);

    private final ParcelBox<FragileParcel> fragileParcelParcelBox = new ParcelBox<>(100);

    @Test
    public void shouldBeCorrectPriceForStandard() {
        int price = standardParcel.calculateDeliveryCost();

        Assertions.assertEquals(20, price, "Полученные суммы не совпадают");
    }

    @Test
    public void shouldBeCorrectPriceForFragile() {
        int price = fragileParcel.calculateDeliveryCost();

        Assertions.assertEquals(330, price, "Полученные суммы не совпадают");
    }

    @Test
    public void shouldBeCorrectPriceForPerishable() {
        int price = perishableParcel.calculateDeliveryCost();

        Assertions.assertEquals(4, price, "Полученные суммы не совпадают");
    }

    @Test
    public void shouldBeNotExpiredBeforeExpirationDate() {
        Assertions.assertFalse(perishableParcel.isExpired(7));
    }

    @Test
    public void shouldBeExpiredInExpirationDate() {
        Assertions.assertTrue(perishableParcel.isExpired(11));
    }

    @Test
    public void shouldBeExpiredAfterExpirationDate() {
        Assertions.assertTrue(perishableParcel.isExpired(15));
    }

    @Test
    public void shouldAddInBox() {
        standardParcelParcelBox.addParcel((StandardParcel) standardParcel);

        Assertions.assertEquals(standardParcel, standardParcelParcelBox.getInBox().get(0));
    }

    @Test
    public void shouldNotAddInBox() {
        fragileParcelParcelBox.addParcel((FragileParcel) fragileParcel);

        Assertions.assertEquals(0, fragileParcelParcelBox.getInBox().size());
    }
}
