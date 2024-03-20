package utils;

import MainPage.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    private static final String url = "https://nexign.com/ru";

    @BeforeEach
    protected void init(){
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        Configuration.browser = "chrome";
        Selenide.open(url);
    }

    @AfterEach
    protected void close(){
        Selenide.close();
    }
}