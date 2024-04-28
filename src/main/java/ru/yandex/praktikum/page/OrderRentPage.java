package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderRentPage {
    private final WebDriver driver;
    //поле Когда привезти самокат
    private final By dateDeliveryLocator = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //поле Срок аренды
    private final By rentalPeriodLocator = By.xpath(".//div[text()='* Срок аренды']");
    //значение из списка периода доставки
    private final String periodDay = ".//div[text()='%s']";
    //чек-бокс при выборе цвета
    private final String colorCheckBox = ".//input[@id='%s']";
    //поле Комментарий для курьера
    private final By commentCourierLocator = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать для окончательного оформления заказа
    private final By buttonOrderLastLocator = By.xpath(".//button[text()='Заказать' and @class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Кнопка подтверждения заказа в модальном окне
    private final By buttonOrderYesLocator = By.xpath(".//button[text()='Да' and @class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Текст "Заказ оформлен"
    private final By orderDoneLocator = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputOrderRent(String date, String period, String color, String comment) {
        //заполнить поле когда привезти самокат
        WebElement dateDelivery = driver.findElement(dateDeliveryLocator);
        dateDelivery.sendKeys(date, Keys.ENTER);
        //заполнить поле срок аренды
        WebElement rentalPeriod = driver.findElement(rentalPeriodLocator);
        rentalPeriod.click();
        WebElement oneDay = driver.findElement(By.xpath(String.format(periodDay, period)));
        oneDay.click();
        //заполнить поле цвет самоката
        WebElement blackCheckBox = driver.findElement(By.xpath(String.format(colorCheckBox, color)));
        blackCheckBox.click();
        //заполнить поле комментарий
        WebElement commentCourier = driver.findElement(commentCourierLocator);
        commentCourier.sendKeys(comment);
    }

    public void clickButtonOrderLast() {
        //кнопка Заказать
        WebElement buttonOrderLast = driver.findElement(buttonOrderLastLocator);
        buttonOrderLast.click();
    }

    public void clickButtonOrderYes() {
        //модалка - кнопка Да
        WebElement buttonOrderYes = driver.findElement(buttonOrderYesLocator);
        buttonOrderYes.click();
    }

    public boolean orderDoneDesplayed() {
        return driver.findElement(orderDoneLocator).isDisplayed();
    }
}
