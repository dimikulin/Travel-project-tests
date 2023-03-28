package pl.seleniumcourse.tests;

import com.aventstack.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import com.aventstack.extentreports.reporter.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuit(){
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void afterSuit(){
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup(){
        PageFactory.initElements(driver, this);
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        //driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS); // czeka na każdyu element 10 sekund jesli nie ma na stronie, jak nie znajdzie to wywali bład
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
