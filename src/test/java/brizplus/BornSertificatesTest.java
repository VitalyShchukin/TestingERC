package brizplus;

import java.sql.SQLException;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BornSertificatesTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static NavigateMIS mis;
    private static NavigateN2O n2o;
    private static final String START_URL = "https://mis-test-app.tambov.gov.ru/";
    private static final String USER = "user3";
    private static final String PASS = "Qwe147asd";

    @BeforeClass
    public static void authorizationAndGoToCertificates() throws InterruptedException {

//        System.setProperty("webdriver.gecko.driver", "/home/vitaly/geckodriver");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "/home/vitaly/chromedriver");
        driver = new ChromeDriver();
        
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mis = new NavigateMIS();
        n2o = new NavigateN2O();

        driver.get(START_URL); //link to checking page
        mis.authorization(driver, USER, PASS); //authorization

//        Thread.sleep(10000); //wait for the page to load

        mis.goToPharmacy(driver); //go to "Аптека. Документы"(для вызова контекста)

        n2o.selectionRCK(driver); //call context and setup "РЦК"
        n2o.setupMedOrg(driver); //setup "Луки"

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        n2o.goToCertificates(driver);//переход в Электронные родовые сертификаты
    }

    @AfterClass
    public static void finishTesting_success() {
        driver.quit();
    }

    @Test
    public void walkAlongN2O() throws SQLException, ClassNotFoundException, InterruptedException {

        DataForTests dataForTests = new DataForTests();
        n2o.createNewCertificate(driver,
                dataForTests.checkDate(), dataForTests.getXDate(),
                dataForTests.getGestAge(), dataForTests.getPatient());//создание нового сертификата

        String birthDateFullNameSnilsDB = dataForTests.getDataFromDB();
        System.out.println(birthDateFullNameSnilsDB);

        String birthDateFullNameSnilsInterf = n2o.findFields(driver, dataForTests.getSnils());
        System.out.println(birthDateFullNameSnilsInterf);

        if (birthDateFullNameSnilsDB.equals(birthDateFullNameSnilsInterf)) {
            System.out.println("Данные в базе и в интерфейсе идентичны");
        } else {
            System.out.println("Данные в базе и в интерфейсе разные");
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        n2o.sendToFSS(driver);
    }
}
