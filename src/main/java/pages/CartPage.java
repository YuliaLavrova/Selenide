package pages;

import org.openqa.selenium.By;

import java.util.List;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CartPage{

    private String closeCartBtn = ".sc-1h98xa9-0.gFkyvN";

    private String itemInCart = ".sc-11uohgb-2.elbkhN";

    public String nameOfItemInCart() {
        return $(By.cssSelector(itemInCart)).text();
    }

    public List<String> nameOfAllItemsInCart() {
        return $$(By.cssSelector(itemInCart)).texts();
    }
    public void closeCartBtn() {
        $(By.cssSelector(closeCartBtn)).click();
    }
}