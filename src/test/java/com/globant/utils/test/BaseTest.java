package com.globant.utils.test;

import com.globant.pages.BasePage;
import com.globant.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    private WebDriver driver;

    private WebDriverWait wait;

    private BasePage basePage;

    private HomePage homePage;

    @BeforeTest
    @Parameters({"url", "user", "password"})
    public void setUp(String url, String user, String password) {
        setupDriver(url);
        basePage = new BasePage(url, user, password);
        homePage = new HomePage(driver);
    }

    private void setupDriver(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public BasePage getBasePage() {
        return basePage;
    }

    public HomePage getHomePage() {
        return homePage;
    }
}
