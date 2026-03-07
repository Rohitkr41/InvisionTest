
package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import pages.PatientTypePage;
import pages.FollowUpRegistrationPage;
import utils.ConfigReader;

public class FollowUpRegistrationTest extends BaseTest {

    @Test
    public void followUpRegistration() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Open Registration
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();
        sidebar.clickRegistration();

        // Select FollowUp Patient Type
        PatientTypePage patientType = new PatientTypePage(driver);
        patientType.selectFollowUp();

        // FollowUp Registration Flow
        FollowUpRegistrationPage followup = new FollowUpRegistrationPage(driver);

//        followup.enterPatientFirstName("Test");
        followup.enterMemberNumber("IH-IVC-26-0379");
//        followup.enterPhoneNumber("9876543210");

        followup.clickSearch();
        followup.clickFetch();
        followup.clickYes();

        followup.clickDiscountCheckbox();
        followup.enterDiscountAmount("5");

        followup.selectDiscountRemark("Poor Patient");

        followup.selectMode("UPI");

        followup.enterTransactionId("TXN12345");

//        followup.clickRegisterPatient();

        System.out.println("FollowUp Registration Completed");
    }
}