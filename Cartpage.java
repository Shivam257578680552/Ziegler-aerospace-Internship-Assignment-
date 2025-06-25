package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By cartLink = By.cssSelector("#main_menu .menu_login .cart");
    private By productRows = By.cssSelector("table.cartTable tbody tr");

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public boolean verifyProductsInCart(List<String> productNames) {
        List<WebElement> rows = driver.findElements(productRows);
        int matchCount = 0;
        for (WebElement row : rows) {
            String rowText = row.getText();
            for (String name : productNames) {
                if (rowText.contains(name)) {
                    matchCount++;
                }
            }
        }
        return matchCount >= productNames.size();
    }

    public void proceedToCheckout() {
        driver.findElement(By.cssSelector("a[title='Checkout']")).click();
    }

    public double getTotalCost() {
        try {
            String totalText = driver.findElement(By.cssSelector(".total > .price")).getText();
            return Double.parseDouble(totalText.replace("$", ""));
        } catch (Exception e) {
            return 0.0;
        }
    }
}
