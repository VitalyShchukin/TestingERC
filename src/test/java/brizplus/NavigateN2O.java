package brizplus;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class NavigateN2O {
    private static WebDriverWait wait;
    private String fullBirthDateNameSnilsFromInterface;

    public void selectionRCK(WebDriver driver) {
        driver.findElement(By.cssSelector("li.context-loop:nth-child(1)"))
                .click();
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/"
                + "div[2]/div/div[2]/div/div/div/ul[3]/li[1]/div/div/a[4]"))
                .click();
    }

    public void setupMedOrg(WebDriver driver) {
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/"
                + "div/div/div/div/section/div/div/div/div/div/div/div[2]/form/"
                + "div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div/div[1]/"
                + "div/div/button"))
                .click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/"
                + "div/div/div/div/section/div/div/div/div/div/div/div[2]/form/"
                + "div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div/div[2]/"
                + "div[1]/input"))
                .sendKeys("луки");
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/"
                + "div/div/div/div/section/div/div/div/div/div/div/div[2]/form/"
                + "div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div/div[2]/"
                + "div[1]/span/i"))
                .click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/"
                + "div/div/div/div/section/div/div/div/div/div/div/div[2]/form/"
                + "div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div/div[2]/"
                + "div[3]/ul/li/a"))
                .click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/"
                + "div/div/div/div/section/div/div/div/div/div/div/div[3]/button"))
                .click();
    }

    public void goToCertificates(WebDriver driver) {
        driver.findElement(By.cssSelector(".header-moduleName"))
                .click();
        driver.findElement(By.linkText("Электронные родовые сертификаты"))
                .click();
    }

    public void createNewCertificate(WebDriver driver, String checkDate, String xDate, String gestAge, String patient) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("ul.nav:nth-child(14) > li:nth-child(1)"))
                .click();
        driver.findElement(By.cssSelector("div.n2o-action-container_radius:"
                + "nth-child(1) > button:nth-child(1)"))
                .click();
        driver.findElement(By.linkText("Создать новый"))
                .click();

        //заполнение полей "Создать электронный родовой сертификат"
        Thread.sleep(500);
        driver.findElement(By.cssSelector("div.n2o-field-label-align-right:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"))
                .sendKeys(checkDate);
        Thread.sleep(500);
        driver.findElement(By.cssSelector("div.n2o-field-label-align-right:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)"
                + " > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"))
                .sendKeys(xDate);
        Thread.sleep(500);
        driver.findElement(By.cssSelector("div.n2o-field-label-align-right:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(3)"
                + " > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)"
                + " > div:nth-child(1) > span:nth-child(1) > input:nth-child(1)"))
                .sendKeys(gestAge);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".n2o-control-container-with-actions"
                + " > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"
                + " > div:nth-child(1) > button:nth-child(1)"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".n2o-control-container-with-actions"
                + " > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)"
                + " > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)"
                + " > input:nth-child(1)"))
                .sendKeys(patient);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/"
                + "div/div/div/section/div/div[1]/div/div/div[2]/div/div/div/"
                + "form/div/div[2]/div/div/div[4]/div/div/div[1]/div[1]/div/"
                + "div/div[2]/div[1]/span/i"))
                .click();
        Thread.sleep(8000); //явное ожидание выпадающего списка

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/"
                + "div/div/div/div/section/div/div[1]/div/div/div[2]/div/div/"
                + "div/form/div/div[2]/div/div/div[4]/div/div/div[1]/div[1]/"
                + "div/div/div[2]/div[3]/ul/li[1]/a"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".n2o-confirm"))
                .click();//сохранение
    }

    public String findFields(WebDriver driver, String snils) {
        driver.findElement(By.cssSelector("div.n2o-control-container:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1) > input:nth-child(1)"))
                .sendKeys(snils);
        driver.findElement(By.cssSelector(".submit"))
                .click();
        driver.findElement(By.cssSelector(".info"))
                .click();
        driver.findElement(By.cssSelector("div.n2o-action-container_radius:nth-child(5) > button:nth-child(1)"))
                .click();

        String name = driver.findElement(By.cssSelector("div.erc-form-column-one > div:nth-child(3) > div:nth-child(1)"
                + " > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1) > input:nth-child(1)"))
                .getAttribute("value");//имя
        String patrName = driver.findElement(By.cssSelector("div.erc-form-column-one > div:nth-child(4)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)"
                + " > input:nth-child(1)"))
                .getAttribute("value");//отчество
        String surName = driver.findElement(By.cssSelector("div.erc-form-column-one > div:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)"
                + " > input:nth-child(1)")).getAttribute("value");//фамилия
        String bDate = driver.findElement(By.cssSelector("div.erc-form-column-one > div:nth-child(5) > div:nth-child(1)"
                + " > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"))
                .getAttribute("value");//дата рождения
        String snilsFromInterface = driver.findElement(By.cssSelector("div.erc-form-column-three > div:nth-child(2)"
                + " > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)"
                + " > input:nth-child(1)")).getAttribute("value");//снилс
        String numOms = driver.findElement(By.cssSelector("div.erc-form-column-two > div:nth-child(2) > div:nth-child(1)"
                + " > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1) > input:nth-child(1)"))
                .getAttribute("value");//омс
        driver.findElement(By.cssSelector(".close")).click();
        fullBirthDateNameSnilsFromInterface = bDate + " "
                + name + " " + patrName + " " + surName + " " + snilsFromInterface + " " + numOms;
        return fullBirthDateNameSnilsFromInterface;
    }

    public void sendToFSS(WebDriver driver) {
        driver.findElement(By.cssSelector("div.n2o-action-container_radius:nth-child(9)"))
                .click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector(".n2o-select")).click();
    }
}
