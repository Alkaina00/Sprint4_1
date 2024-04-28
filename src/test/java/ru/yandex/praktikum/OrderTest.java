package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderClientPage;
import ru.yandex.praktikum.page.OrderRentPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest{
    private String buttonLocation;
    private String nameUser;
    private String lastNameUser;
    private String adressUser;
    private String subweyName;
    private String subweyColor;
    private String numberPhone;

    private String dateDelivery;
    private String period;
    private String colorScooter;
    private String comment;

    public OrderTest(String buttonLocation, String nameUser, String lastNameUser, String adressUser,
                     String subweyName, String subweyColor, String numberPhone, String dateDelivery, String period,
                     String colorScooter, String comment){
        this.buttonLocation = buttonLocation;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.adressUser = adressUser;
        this.subweyName = subweyName;
        this.subweyColor = subweyColor;
        this.numberPhone = numberPhone;

        this.dateDelivery = dateDelivery;
        this.period = period;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials(){
        return new Object[][]{
                {"Header", "Иван", "Иванов", "г. Москва, ул. Ивановская, д.1", "Арбатская", "background-color: rgb(77, 198, 244);",
                        "79049678723", "01.01.2025", "сутки", "black", "Перед доставкой позвонить за 15 минут"},
                {"Home", "Ольга", "Ивановна", "г. Москва, ул. Ивановская, д.30", "Арбатская", "background-color: rgb(44, 117, 196);",
                        "79049678799", "30.06.2024", "пятеро суток", "grey", "Перед доставкой позвонить за час"},
        };
    }

    @Test
    public void orderCreate() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.closeCookies();
        mainPage.clickOrderButton(buttonLocation);

        OrderClientPage orderClientPage = new OrderClientPage(webDriver);
        orderClientPage.inputOrderClient(nameUser, lastNameUser, adressUser, subweyName, subweyColor, numberPhone);
        orderClientPage.clickButtonNextOrder();

        OrderRentPage orderRentPage = new OrderRentPage(webDriver);
        orderRentPage.inputOrderRent(dateDelivery, period, colorScooter, comment);
        orderRentPage.clickButtonOrderLast();

        orderRentPage.clickButtonOrderYes();

        //появилось окно Заказ оформлен? да - успех, нет - провал
        assertTrue(orderRentPage.orderDoneDesplayed());
    }
}
