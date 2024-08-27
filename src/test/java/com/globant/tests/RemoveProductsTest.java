package com.globant.tests;


import com.globant.pages.BasePage;
import com.globant.pages.HomePage;
import com.globant.utils.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static com.globant.pages.BasePage.*;


public class RemoveProductsTest {
    BasePage basePage;
    HomePage homePage;

    @BeforeTest
    @Parameters({"url","user","password"})
    public void setUp(String url,String user,String password) {
        basePage = new BasePage(url,user,password);
        homePage = new HomePage(getDriver());
    }

    @Test
    public void removeProductsFromTheCart() {
        List<WebElement> products = homePage.getProductButtonsList();
        Collections.shuffle(products);
        log.info("The number of products available are: {}", products.size());
        for (int i = 0; i <=2; i++) {
            products.get(i).click();
       }
        Assert.assertEquals(homePage.getCart().getText(), "3");
        log.info("The three products are successfully added to the cart");
        homePage.getCart().click();
        List<WebElement> addedProducts = driver.findElements(By.className("cart_button"));
        log.info("The number of added products is: {}", addedProducts.size());
        for (WebElement product : addedProducts) {
            product.click();
        }
        Assert.assertEquals(homePage.getCart().getText(), "");
        log.info("The three products are successfully removed from the cart");
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
