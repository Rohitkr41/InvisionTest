package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import pages.PatientTypePage;
import utils.ConfigReader;

public class FollowUpRegistrationTest extends BaseTest {

    @Test
    public void followUpRegistration() {

        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();
        sidebar.clickRegistration();

        PatientTypePage patientType = new PatientTypePage(driver);
        patientType.selectFollowUp();

        System.out.println("Followup Patient Type Selected");
    }
}
