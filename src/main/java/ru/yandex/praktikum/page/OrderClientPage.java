package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderClientPage {
    private final WebDriver driver;
    //поле Имя
    private final By nameUserLocator = By.xpath(".//input[@placeholder = '* Имя']");
    //поле Фамилия
    private final By lastNameUserLocator = By.xpath(".//input[@placeholder = '* Фамилия']");
    //поле Адрес: куда привезти заказ
    private final By addressOrderLocator = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //поле Станция метро
    private final By subweyInputLocator = By.xpath(".//input[@placeholder = '* Станция метро']");
    //выбор из списка по наименованию станции и цвету ветки
    private final String subweyStationLocator = ".//button[./div[text()='%s']][./div[@style='%s']]";
    //поле Телефон
    private final By numberPhoneLocator = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By buttonNextLocator = By.xpath(".//button[text()='Далее']");


    public OrderClientPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputOrderClient(String name, String lastName, String adress, String subweyTitle,
                                 String subweyColor, String phone) {
        //заполнить поле Имя
        WebElement nameUser = driver.findElement(nameUserLocator);
        nameUser.sendKeys(name);
        //заполнить поле Фамилия
        WebElement lastNameUser = driver.findElement(lastNameUserLocator);
        lastNameUser.sendKeys(lastName);
        //заполнить поле Адрес
        WebElement addressOrder = driver.findElement(addressOrderLocator);
        addressOrder.sendKeys(adress);
        //заполнить поле Станция метро
        WebElement subweyInput = driver.findElement(subweyInputLocator);
        subweyInput.click();
        WebElement subweyStation = driver.findElement(By.xpath(String.format(subweyStationLocator, subweyTitle, subweyColor)));
        subweyStation.click();
        //заполнить поле Телефон
        WebElement numberPhone = driver.findElement(numberPhoneLocator);
        numberPhone.sendKeys(phone);
    }

    public void clickButtonNextOrder() {
        //кнопка Далее
        WebElement buttonNext = driver.findElement(buttonNextLocator);
        buttonNext.click();
    }
}
