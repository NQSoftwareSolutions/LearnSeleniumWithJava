import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import javax.crypto.ExemptionMechanismException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class GoogleSearchTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        //launch browser
        System.setProperty("webdriver.chrome.driver", "E:\\\\Softwares\\\\drivers\\\\chromedriver.exe");
        driver = new ChromeDriver();
        //maximize the browser window
        driver.manage().window().maximize();
        //Navigate to google
        driver.get("https://www.google.com/");
    }
    @Test
    public void testGoogleSearch(){
        //Find text input field by its name
        WebElement searchInputField = driver.findElement(By.name("q"));
        //clear the existing text value
        searchInputField.clear();
        //Enter Something for search
        searchInputField.sendKeys("abdul qayoom is starting learn selenium using java.");
        //Now submit the form
        searchInputField.submit();
        //Google's search is randered dynamically with javascript.
        //wait for page to load & Time out after 10 seconds.
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until((ExpectedCondition<Boolean>) d -> {
                    assert d != null;
                    return d.getTitle()
                            .toLowerCase()
                            .startsWith("abdul qayoom is starting");
                });
        assertEquals("abdul qayoom is starting learn selenium using java. - Google Search"
                , driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        //Close Browser
        driver.quit();
    }
}
