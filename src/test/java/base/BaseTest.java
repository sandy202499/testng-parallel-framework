package base;

import framework.ConfigReader;
import framework.DriverFactory;
import framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {		//@Optional means “Before every test, get the browser value from TestNG. If no browser value is provided, use Chrome by default.”

        WebDriver driver = DriverFactory.createDriver(browser);

        // Store THIS browser in THIS thread only.
        DriverManager.setDriver(driver);

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage()
                .timeouts().implicitlyWait(Duration.ofSeconds(5));

        DriverManager.getDriver().get(ConfigReader.get("baseUrl"));

   /*     System.out.println(
                "SETUP -> Thread: "
                + Thread.currentThread().getId()
                + " | Driver: "
                + DriverManager.getDriver()
                + " | Browser: "
                + browser);			*/
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

    /*    System.out.println(
                "TEARDOWN -> Thread: "
                + Thread.currentThread().getId());		*/

        DriverManager.quitDriver();
    }
}
