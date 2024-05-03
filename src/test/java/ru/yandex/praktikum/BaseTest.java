package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver webDriver;
    private static final String URL_TEST = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get(URL_TEST);
    }

    @After
    public void teardown() {
        // Закрой браузер
        webDriver.close();
    };
}
