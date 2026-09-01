package tests;

import base.BaseTest;
import framework.ConfigReader;
import framework.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginTest() {

     /*   System.out.println(
                "validLoginTest -> Thread: "
                + Thread.currentThread().getId());		*/

        LoginPage loginPage = new LoginPage();

        loginPage.login(
                ConfigReader.get("standardUsername"),
                ConfigReader.get("standardPassword"));

        ProductsPage productsPage = new ProductsPage();

        Assert.assertEquals(productsPage.getPageTitle(),"Products");
        
     // For testing screenshot capture on failure:
     // Assert.assertEquals(productsPage.getPageTitle(), "Wrong Title");
        
        System.out.println(
                "validLoginTest URL -> "
                + DriverManager.getDriver().getCurrentUrl());
    }

    @Test
    public void lockedOutUserTest() {

      /*  System.out.println(
                "lockedOutUserTest -> Thread: "
                + Thread.currentThread().getId());		*/

        LoginPage loginPage = new LoginPage();

        loginPage.login(
                ConfigReader.get("lockedUsername"),
                ConfigReader.get("standardPassword"));

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("locked out"));
    }
}
