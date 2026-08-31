package pages;

import framework.DriverManager;
import org.openqa.selenium.By;

public class ProductsPage {

    private final By pageTitle =
            By.cssSelector("[data-test='title']");

    public String getPageTitle() {
        return DriverManager.getDriver()
                .findElement(pageTitle)
                .getText();
    }
}
