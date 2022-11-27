import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearch {
    @Test
    public void searchHotel(){
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS); // czeka na każdyu element 10 sekund jesli nie ma na stronie, jak nie znajdzie to wywali bład
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click(); //klikamy w pole
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai"); //wprowadzamy wartosc
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).sendKeys("17/12/2022");
        driver.findElement(By.name("checkout")).sendKeys("20/12/2022");

        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click()); // klikanie w kalendarzu - trzeba bylo wziac do listy bo kilka znajduwało a my bierzemy tylko ten widoczny dla nas
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text() = ' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b")).stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList()); //pobranie listy hoteli zmieneilismy z getText na getAttribute bo tylko 2 znajdywało hotele
//        System.out.println(hotelNames.size());
//        hotelNames.forEach(el -> System.out.println(el));
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }
}
