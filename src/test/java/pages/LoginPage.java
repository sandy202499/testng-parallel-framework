package pages;

import framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    public void login(String user, String pass) {
        driver().findElement(username).sendKeys(user);
        driver().findElement(password).sendKeys(pass);
        driver().findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver().findElement(errorMessage).getText();
    }
}
