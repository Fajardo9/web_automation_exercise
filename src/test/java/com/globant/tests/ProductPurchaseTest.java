package com.globant.tests;

import com.globant.pages.BasePage;
import com.globant.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.ThreadLocalRandom;

import static com.globant.pages.BasePage.driver;
import static com.globant.pages.BasePage.log;


public class ProductPurchaseTest {
    BasePage basePage;
    HomePage homePage;

    @DataProvider(name = "persona")
    public Object[][] dataProvider() {
        Object[][] data = new Object[][] {{"Alejandro","Fajardo","761540"},{"Valentina","Henao","761540"}};
        return new Object[][] {data[1]};
    }

    @BeforeTest
    @Parameters({"url","user","password"})
    public void setUp(String url, String user, String password) {
        basePage = new BasePage(url,user,password);
        homePage = new HomePage(BasePage.getDriver());

    }
    @Test(dataProvider = "persona")
    public void verifyPurchaseSuccessForOneItem(String name, String lastName, String postalCode) {
        int randomIndex = ThreadLocalRandom.current().nextInt(homePage.getProductButtonsList().size());
        WebElement addToCartButton = homePage.getProductButtonsList().get(randomIndex);
        addToCartButton.
                click();
        homePage.getCart().
                click();
        homePage.getCheckoutBtn().
                click();
        driver.findElement(By.cssSelector("input[placeholder='First Name']")).
                sendKeys(name);
        driver.findElement(By.cssSelector("input[placeholder='Last Name']")).
                sendKeys(lastName);
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).
                sendKeys(postalCode);
        driver.findElement(By.className("submit-button")).
                click();
        driver.findElement(By.id("finish")).
                click();
        WebElement checkoutDiv = driver.findElement(By.tagName("div")).findElement(By.className("checkout_complete_container"));
        Assert.assertTrue(checkoutDiv.isDisplayed());
        log.info("Checkout successful");
        log.info(checkoutDiv.getText());
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}

