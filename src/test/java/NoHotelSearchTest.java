import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pl.seleniumcourse.pages.HotelSearchPage;
import pl.seleniumcourse.pages.ResultsPage;
import pl.seleniumcourse.tests.BaseTest;

public class NoHotelSearchTest extends BaseTest {

    @Test
    public void searchHotel(){
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("17/12/2022","20/12/2022");
        hotelSearchPage.setTravellers(0,1);
        hotelSearchPage.performSearch();
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertTrue(resultsPage.resultHeadingIsDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");

    }
}
