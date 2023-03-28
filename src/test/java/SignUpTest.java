import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pl.seleniumcourse.tests.BaseTest;
import pl.seleniumcourse.utils.SeleniumHelper;

public class SignUpTest extends BaseTest {


    @Test
    public void signUp(){
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        String name = "Mateusz";
        String surname = "Mikuła";
        driver.findElement(By.name("firstname")).sendKeys(name);
        driver.findElement(By.name("lastname")).sendKeys(surname);
        driver.findElement(By.name("phone")).sendKeys("111111111");
        driver.findElement(By.name("email")).sendKeys("kanapka7@email.com.pl");
        driver.findElement(By.name("password")).sendKeys("haslo123");
        driver.findElement(By.name("confirmpassword")).sendKeys("haslo123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        SeleniumHelper.waitForElementToExist(driver,By.xpath("//h3[@class='RTL']"));
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(surname));
    }
}
