package com.globant.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage{
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final Logger log = LoggerFactory.getLogger(HomePage.class);


    public BasePage(String url,String user, String password) {
        setupDriver(url);
        login(user, password);
    }

    public static void login(String username, String password) {
        driver.findElement(By.cssSelector("[placeholder='Username']"))
                .sendKeys(username);
        driver.findElement(By.id("password"))
                .sendKeys(password);
        driver.findElement(By.className("submit-button"))
                .click();
    }
    private void setupDriver(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
