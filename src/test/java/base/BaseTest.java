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

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            TakesScreenshot screenshot =
                    (TakesScreenshot) DriverManager.getDriver();

            File source =
                    screenshot.getScreenshotAs(OutputType.FILE);

            Path destination = Paths.get(
                    "screenshots",
                    result.getName() + ".png"
            );

            try {
                Files.createDirectories(destination.getParent());
                Files.copy(source.toPath(), destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DriverManager.quitDriver();
    }
}
