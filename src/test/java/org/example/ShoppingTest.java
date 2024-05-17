package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ShoppingTest {

    private static final Logger LOGGER = LogManager.getLogger(ShoppingTest.class);

    @Test
    void addToCartTest() throws InvalidFormatException {
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        String nameOfChosenItem = $(By.cssSelector(".sc-124al1g-4.eeXMBo")).text();
        LOGGER.info(nameOfChosenItem + " is added to cart");
        $(By.xpath("//button[text() = 'Add to cart']")).click();
        $(By.cssSelector(".sc-11uohgb-2.elbkhN")).shouldHave(text(nameOfChosenItem));
    }

    @Test
    public void listAllItemsTest() {
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        List<String> namesStr = $$(By.cssSelector(".sc-124al1g-4.eeXMBo")).texts();
        LOGGER.info(namesStr + " are displayed on the home page");
        executeJavaScript("arguments[0].click();", $$(By.xpath("//button[text() = 'Add to cart']")));
        List<String> namesItemsInCart = $$(By.cssSelector(".sc-11uohgb-2.elbkhN")).texts();
        LOGGER.info(namesItemsInCart + " are added to cart");
        Assert.assertEquals(namesItemsInCart, namesStr);
    }

    @Test
    public void filterItemsTest() {
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        long quantityBeforeFilter = $$(By.cssSelector(".sc-124al1g-4.eeXMBo")).size();
        LOGGER.info("The amount of items before is " + quantityBeforeFilter);
        $(By.xpath("//span[text() = 'S']")).click();
        long quantityAfterFilter = $$(By.cssSelector(".sc-124al1g-4.eeXMBo")).size();
        LOGGER.info("The amount of items after is " + quantityAfterFilter);
        Assert.assertTrue(quantityBeforeFilter > quantityAfterFilter, "Filter isn't working correctly");
    }

    @Test
    public void countItemsAfterFilterTest() {
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        String[] str = $(By.xpath("//main[@class = 'sc-ebmerl-4 iliWeY']/p")).text().split(" ");
        int first = Integer.parseInt(str[0]);
        LOGGER.info("The amount of items before is " + first);
        $(By.xpath("//span[text() = 'S']")).click();
        String[] str1 = $(By.xpath("//main[@class = 'sc-ebmerl-4 iliWeY']/p")).text().split(" ");
        int second = Integer.parseInt(str1[0]);
        LOGGER.info("The amount of items after is " + second);
        Assert.assertTrue(first > second, "Filter isn't working correctly");
    }
}