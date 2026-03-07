package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import pages.PatientTypePage;
import utils.ConfigReader;

public class ReferralRegistrationTest extends BaseTest {

    @Test
    public void referralRegistration() {

        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();
        sidebar.clickRegistration();

        PatientTypePage patientType = new PatientTypePage(driver);
        patientType.selectReferral();

        System.out.println("Referral Patient Type Selected");
    }
}
