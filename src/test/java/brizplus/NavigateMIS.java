package brizplus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigateMIS {

    public void authorization(WebDriver driver, String username, String password) {
        driver.findElement(By.cssSelector("#username"))
                .sendKeys(username);
        driver.findElement(By.cssSelector("#password"))
                .sendKeys(password);
        driver.findElement(By.cssSelector("#submit"))
                .click();
    }

    public void goToPharmacy(WebDriver driver) {
        driver.findElement(By.cssSelector(".common > ul:nth-child(3) > li:nth-child(2) > a:nth-child(1)"))
                .click();
    }
}
