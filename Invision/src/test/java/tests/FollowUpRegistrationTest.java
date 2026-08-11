
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

        followup.enterMemberNumber("IH-IVC-26-0380");
        followup.clickSearch();
        followup.clickFetch();
        followup.clickYes();

        // 🚀 ✅ SMART HANDLING (Payment + Register)
        followup.handlePaymentAndRegister(
                "UPI",              // Mode
                "Poor Patient",    // Discount Remark
                "TXN12345809",        // Transaction ID
                "5"                // Discount Amount
        );

        System.out.println("FollowUp Registration Completed");
    }
}
