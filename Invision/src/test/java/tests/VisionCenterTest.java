

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import pages.RegistrationPage;
import utils.ConfigReader;

public class VisionCenterTest extends BaseTest {

    @Test
    public void openRegistrationFromVisionCenter() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Navigation
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();
        sidebar.clickRegistration();

        // Validation
        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        Assert.assertTrue(
                registrationPage.isRegistrationPageDisplayed(),
                "Registration page not opened"
        );
    }
}