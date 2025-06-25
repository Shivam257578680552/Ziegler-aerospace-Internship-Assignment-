package pages;

import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CSVReader;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRegistrationForm(boolean leaveLastNameEmpty) {
        CSVRecord data = CSVReader.getUserData();

        driver.findElement(By.name("firstname")).sendKeys(data.get("firstName"));

        if (!leaveLastNameEmpty) {
            driver.findElement(By.name("lastname")).sendKeys(data.get("lastName"));
        }

        driver.findElement(By.name("email")).sendKeys(data.get("email"));
        driver.findElement(By.name("password")).sendKeys(data.get("password"));

        // Assume other necessary fields (if any) are optional/skipped
    }

    public void submitForm() {
        driver.findElement(By.cssSelector("button[title='Continue']")).click();
    }

    public boolean isValidationErrorVisible() {
        try {
            WebElement error = driver.findElement(By.cssSelector(".alert.alert-error"));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
