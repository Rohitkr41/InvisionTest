
package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import pages.PatientTypePage;
import pages.ReferralRegistrationPage;
import utils.ConfigReader;

public class ReferralRegistrationTest extends BaseTest {

    @Test
    public void referralRegistration() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Open Vision Center → Registration
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();
        sidebar.clickRegistration();

        // Select Patient Type → Referral
        PatientTypePage patientType = new PatientTypePage(driver);
        patientType.selectReferral();

        // Referral Registration Page Actions
        ReferralRegistrationPage referral = new ReferralRegistrationPage(driver);

        referral.selectSurvey();   // OR referral.selectCommunityClinic()

//        referral.enterPatientFirstName("Rohit");
        referral.enterMemberNumber("228-14-10-09");
//        referral.enterPhoneNumber("9876543210");

        referral.clickSearch();
        referral.clickFetch();
        referral.clickYes();
        
        referral.clickDiscountCheckbox();

        referral.enterDiscountAmount("10");

        referral.selectDiscountRemark("Poor Patient");

        referral.selectMode("UPI");

        referral.enterTransactionId("TXN123456");
        
//        referral.clickRegisterPatient();

        System.out.println("Referral Registration Search Completed");
    }
}
