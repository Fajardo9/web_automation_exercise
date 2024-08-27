package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.globant.pages.BasePage.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    WebDriver driver;


    @FindBy(id = "react-burger-menu-btn")
    private WebElement btnReactBurgerMenu;

    @FindBy(css = "a#logout_sidebar_link")
    private WebElement btnLogout;

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]")
    private WebElement cart;

    @FindBy(css = "button[data-test='checkout']")
    private WebElement checkoutBtn;

    public List<WebElement> getProductButtonsList() {
        wait.until(ExpectedConditions.visibilityOf(getAddToCartButtons().get(0)));
        List<WebElement> productButtonList = new ArrayList<>();
        List<String> productNames;
        productNames = addToCartButtons.stream().map(p -> p.getAttribute("id")).collect(Collectors.toList());
        for (String productName : productNames) {
            WebElement productButtonByID = driver.findElement(By.id(productName));
            productButtonList.add(productButtonByID);
        }
        return productButtonList;
    }

    public HomePage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(driver, this);
        log.info("HomePage items initialized");

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }

    public WebElement getCart() {
        return cart;
    }

    public WebElement getCheckoutBtn() {
        return checkoutBtn;
    }

    public WebElement getBtnReactBurgerMenu() {
        return btnReactBurgerMenu;
    }

    public WebElement getBtnLogout() {
        return btnLogout;
    }
}
