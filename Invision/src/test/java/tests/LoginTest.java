

package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class LoginTest extends base.BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage login = new LoginPage(driver);

        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        login.login(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains("adminDashboard"));

        By dashboardHeader = By.xpath("//h4[contains(text(),'Admin Dashboard')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));

        Assert.assertTrue(driver.findElement(dashboardHeader).isDisplayed(),
                "Login Failed");

        System.out.println("✅ Login Successful");
    }
}