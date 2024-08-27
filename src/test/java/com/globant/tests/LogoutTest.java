package com.globant.tests;

import com.globant.pages.BasePage;
import com.globant.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.globant.pages.BasePage.getDriver;
import static com.globant.pages.BasePage.wait;

public class LogoutTest {
    private static final Logger log = LoggerFactory.getLogger(LogoutTest.class);
    BasePage basePage;
    HomePage homePage;

    @BeforeTest
    @Parameters({"url", "user", "password"})
    public void setUp(String url, String user, String password) {
        basePage = new BasePage(url, user, password);
        homePage = new HomePage(getDriver());
    }

    @Test
    public void testLogout() {
        homePage.getBtnReactBurgerMenu().click();
        wait.until(ExpectedConditions.visibilityOf(homePage.getBtnLogout()));
        homePage.getBtnLogout().click();
        Assert.assertTrue(getDriver().findElement(By.cssSelector("[placeholder='Username']")).isDisplayed());
        log.info("Logged out successfully");
    }

    @AfterTest
    public void tearDown() {
        getDriver().quit();
    }
}
