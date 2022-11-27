import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

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

    }
}
