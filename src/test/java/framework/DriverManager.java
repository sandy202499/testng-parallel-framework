package framework;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    // Each TestNG thread gets its own WebDriver reference.
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized for thread: "
                    + Thread.currentThread().getId());
        }

        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
