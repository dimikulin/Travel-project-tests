package pl.seleniumcourse.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumcourse.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id= "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text() = ' Search']")
    private WebElement searchButton;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void setCity(String cityName){
        logger.info("Setting city "+cityName);
        SeleniumHelper.waitForElementToBeVisible(driver,searchHotelSpan);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = "//span[@class='select2-match' and text()='"+cityName+"']";
        SeleniumHelper.waitForElementToExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city done");
           }

    public void setDates(String checkin, String checkout){
        logger.info("Setting dates check-in: "+checkin+" check-out: "+checkout);
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("Setting dates done");
    }

    public void setTravellers(int adultsToAdd, int childToAdd){
        logger.info("Adding adults: "+adultsToAdd+" and kids: "+childToAdd);
        SeleniumHelper.waitForElementToBeVisible(driver,travellersInput);
        travellersInput.click();
        for(int i=0;i<adultsToAdd;i++){
            SeleniumHelper.waitForElementToBeVisible(driver,adultPlusBtn);
            adultPlusBtn.click();
        }
        for(int i=0;i<childToAdd;i++){
            SeleniumHelper.waitForElementToBeVisible(driver,checkoutInput);
            checkoutInput.click();
        }
        logger.info("Adding travelers done");
    }

    public void performSearch(){
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing search done");
    }
}


