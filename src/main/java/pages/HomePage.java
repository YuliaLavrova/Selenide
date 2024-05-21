package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HomePage {

    private String addToCartBtn = "//button[text() = 'Add to cart']";

    private String item = ".sc-124al1g-4.eeXMBo";

    private String amountOfItems = "//main[@class = 'sc-ebmerl-4 iliWeY']/p";

    private String sSize = "//span[text() = 'S']";

    private String openCartBtn = "//button[@class = 'sc-1h98xa9-0 gFkyvN']";

    public void openUrl() {
        open("https://react-shopping-cart-67954.firebaseapp.com/");
    }

    public String nameOfItem() {
        return $(By.cssSelector(item)).text();
    }

    public List<String> namesOfAllItems() {
        return $$(By.cssSelector(item)).texts();
    }

    public CartPage clickAllAddToCartBtn() {
        ElementsCollection list= $$(By.xpath(addToCartBtn));
        for (SelenideElement items : list){
            ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].click();", items.toWebElement());
        };
        return new CartPage();
    }

    public int countItemsDisplayed() {
        String amountStr = $(By.xpath(amountOfItems)).text();
        String[] str = amountStr.split(" ");
        int amount = Integer.parseInt(str[0]);
        return amount;
    }

    public void filterItemsBySSize() throws InterruptedException {
        $(By.xpath(sSize)).click();
        Thread.sleep(5000);
    }

    public CartPage clickAddToCartFirstBtn() {
        $(By.xpath(addToCartBtn)).click();
        return new CartPage();
    }

    public void openCart() {
        $(By.xpath(openCartBtn)).click();
    }
}
