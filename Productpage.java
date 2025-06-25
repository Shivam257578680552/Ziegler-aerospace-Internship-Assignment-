package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductPage {
    WebDriver driver;
    Random random = new Random();
    public static List<Map<String, String>> selectedProducts = new ArrayList<>();

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productNames = By.cssSelector(".fixed_wrapper .prdocutname");

    public void addRandomProductsToCart(int count) {
        List<WebElement> products = driver.findElements(productNames);
        Set<Integer> chosenIndexes = new HashSet<>();

        while (chosenIndexes.size() < count && chosenIndexes.size() < products.size()) {
            int index = random.nextInt(products.size());
            if (chosenIndexes.contains(index)) continue;

            WebElement product = products.get(index);
            chosenIndexes.add(index);

            try {
                String name = product.getText();
                String url = product.getAttribute("href");
                product.click();

                String price = driver.findElement(By.cssSelector(".productfilneprice")).getText();
                WebElement addToCart = driver.findElement(By.cssSelector(".cart > a"));

                Map<String, String> productInfo = new HashMap<>();
                productInfo.put("name", name);
                productInfo.put("price", price);
                productInfo.put("url", url);
                productInfo.put("quantity", "1");

                selectedProducts.add(productInfo);
                addToCart.click();
                Thread.sleep(1000); // brief wait after adding
                driver.navigate().back();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getCartCount() {
        try {
            String text = driver.findElement(By.cssSelector("#main_menu span.cart_total")).getText();
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }
}
