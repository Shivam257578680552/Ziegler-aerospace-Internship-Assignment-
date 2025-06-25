package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By categoryLinks = By.cssSelector("ul.nav-pills > li > a");

    public void navigateToHomePage() {
        driver.get("https://automationteststore.com/");
    }

    public void printAllCategories() {
        List<WebElement> categories = driver.findElements(categoryLinks);
        System.out.println("Main Categories:");
        for (WebElement cat : categories) {
            System.out.println("- " + cat.getText());
        }
    }

    public void clickRandomCategory() {
        List<WebElement> categories = driver.findElements(categoryLinks);
        if (categories.size() >= 1) {
            int index = new Random().nextInt(categories.size());
            categories.get(index).click();
        }
    }

    public boolean hasAtLeastThreeProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector(".fixed_wrapper .prdocutname"));
        return products.size() >= 3;
    }
}
