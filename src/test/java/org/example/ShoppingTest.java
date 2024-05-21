package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ShoppingTest {

    private static final Logger LOGGER = LogManager.getLogger(ShoppingTest.class);

    @Test
    void addToCartTest() {
        HomePage homePage = new HomePage();
        homePage.openUrl();
        String nameOfChosenItem = homePage.nameOfItem();
        LOGGER.info(nameOfChosenItem + " is chosen");
        CartPage cartPage  = homePage.clickAddToCartFirstBtn();
        LOGGER.info(cartPage.nameOfItemInCart() + " is added to cart");
        Assert.assertEquals(cartPage.nameOfItemInCart(), nameOfChosenItem, "Item in the cart doesn't match item added");

    }

    @Test
    public void listAllItemsTest() {
        HomePage homePage = new HomePage();
        homePage.openUrl();
        List<String> namesStr = homePage.namesOfAllItems();
        LOGGER.info(namesStr + " are displayed on the home page");
        CartPage cartPage = homePage.clickAllAddToCartBtn();
        List<String> namesItemsInCart = cartPage.nameOfAllItemsInCart();
        LOGGER.info(namesItemsInCart + " are added to cart");
        Assert.assertEquals(namesItemsInCart, namesStr, "Items in the cart don't match items added");
    }

    @Test
    public void filterItemsTest() throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.openUrl();
        long quantityBeforeFilter = homePage.namesOfAllItems().size();
        LOGGER.info("The amount of items before is " + quantityBeforeFilter);
        homePage.filterItemsBySSize();
        long quantityAfterFilter = homePage.namesOfAllItems().size();
        LOGGER.info("The amount of items after is " + quantityAfterFilter);
        Assert.assertTrue(quantityBeforeFilter > quantityAfterFilter, "Filter isn't working correctly");
    }

    @Test
    public void countItemsAfterFilterTest() throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.openUrl();
        int first = homePage.countItemsDisplayed();
        LOGGER.info("The amount of items before is " + first);
        homePage.filterItemsBySSize();
        int second = homePage.countItemsDisplayed();
        LOGGER.info("The amount of items after is " + second);
        Assert.assertTrue(first > second, "Filter isn't working correctly");
    }
}