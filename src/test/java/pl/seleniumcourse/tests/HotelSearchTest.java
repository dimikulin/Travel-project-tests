package pl.seleniumcourse.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumcourse.pages.HotelSearchPage;
import pl.seleniumcourse.pages.ResultsPage;
import pl.seleniumcourse.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import pl.seleniumcourse.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {
    @Test
    public void searchHotel () throws IOException {

        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchTest = new HotelSearchPage(BaseTest.driver);
        hotelSearchTest.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchTest.setDates("17/12/2022", "20/12/2022");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchTest.setTravellers(1,1);
        test.log(Status.PASS, "Setting travelers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchTest.performSearch();
        test.log(Status.PASS, "Performing search done");
        test.log(Status.PASS, "Screenshot", SeleniumHelper.getScreenshot(driver));


        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();
//        System.out.println(hotelNames.size());
//        hotelNames.forEach(el -> System.out.println(el));
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));

    }
@Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider (String city, String hotel) throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test With Data Provider for city "+city);
        HotelSearchPage hotelSearchTest = new HotelSearchPage(BaseTest.driver);
        hotelSearchTest.setCity(city);
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchTest.setDates("17/12/2022", "20/12/2022");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchTest.setTravellers(1,1);
        test.log(Status.PASS, "Setting travelers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchTest.performSearch();
        test.log(Status.PASS, "Performing search done");

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();
        Assert.assertEquals(hotelNames.get(0),hotel);
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("textData.xlsx");
    }
}
