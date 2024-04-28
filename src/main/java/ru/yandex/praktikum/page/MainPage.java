package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver driver;
    //Кнопка Заказать
    private final String buttonOrder1 = ".//div[contains(@class, '%s')]/button[text()='Заказать']";

    //Кнопка закрыть сообщение о куки
    private final By buttonCloseCookiesLocator = By.id("rcc-confirm-button");

    //Текст "Вопросы о важном"
    private final By questionHeadLocator = By.xpath(".//div[text()='Вопросы о важном']");
    //Важный вопрос
    private final String importantQuestion = "accordion__heading-%s";
    //Ответ на вопрос
    private final String importantAnswer = ".//div[@id='accordion__panel-%s']/p";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton(String button){
        WebElement buttonOrder = driver.findElement(By.xpath(String.format(buttonOrder1, button)));
        //Проскролить страницу до кнопки Заказать
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", buttonOrder);
        new WebDriverWait(driver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(buttonOrder));
        buttonOrder.click();
    }

    public void closeCookies(){
        WebElement buttonCloseCookies = driver.findElement(buttonCloseCookiesLocator);
        buttonCloseCookies.click();
    }

    public String clickImportantQuestion(int index){
        WebElement questionHead = driver.findElement(questionHeadLocator);
        //Проскролить страницу до заголовка вопросов
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionHead);
        //Нажать на вопрос
        WebElement question = driver.findElement(By.id(String.format(importantQuestion, index)));
        new WebDriverWait(driver, ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(question));
        question.click();

        WebElement answer = driver.findElement(By.xpath(String.format(importantAnswer, index)));
        new WebDriverWait(driver, ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(answer));
        return answer.getText();
    }
}
