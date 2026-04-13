//
//package tests.report;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import base.BaseTest;
//import pages.LoginPage;
//import pages.SidebarPage;
//import pages.report.SurveyRegistrationReportPage;
//import utils.ConfigReader;
//
//public class SurveyRegistrationReportTest extends BaseTest {
//
//    private SurveyRegistrationReportPage page;
//
//    @BeforeMethod
//    public void setupPage() {
//
//        LoginPage login = new LoginPage(driver);
//        login.login(
//                ConfigReader.getProperty("username"),
//                ConfigReader.getProperty("password")
//        );
//
//        SidebarPage sidebar = new SidebarPage(driver);
//        sidebar.openReport();
//        sidebar.clickSurveyRegistration();
//
//        page = new SurveyRegistrationReportPage(driver);
//    }
//
//    @Test
//    public void topSearchByGroupHospital() {
//
//        page.topSearch(
//                ConfigReader.getProperty("survey.group"),
//                ConfigReader.getProperty("survey.hospital")
//        );
//
//        Assert.assertTrue(page.isResultDisplayed(), "Top Search failed");
//    }
//
//    @Test
//    public void advanceSearchByPatientName() {
//
//        page.searchByPatientAndDate(
//                ConfigReader.getProperty("survey.group"),
//                ConfigReader.getProperty("survey.hospital"),
//                "Rohit",
//                null,
//                null
//        );
//
//        Assert.assertTrue(page.isResultDisplayed(), "Patient search failed");
//    }
//
//    @Test
//    public void advanceSearchByPhone() {
//
//        page.searchByPhoneAndDate(
//                ConfigReader.getProperty("survey.group"),
//                ConfigReader.getProperty("survey.hospital"),
//                "9999999999",
//                null,
//                null
//        );
//
//        Assert.assertTrue(page.isResultDisplayed(), "Phone search failed");
//    }
//
//    @Test
//    public void advanceSearchByMember() {
//
//        page.searchByMemberAndDate(
//                ConfigReader.getProperty("survey.group"),
//                ConfigReader.getProperty("survey.hospital"),
//                "123",
//                null,
//                null
//        );
//
//        Assert.assertTrue(page.isResultDisplayed(), "Member search failed");
//    }
//
//    @Test
//    public void advanceSearchByDate() {
//
//        page.searchByDate(
//                ConfigReader.getProperty("survey.group"),
//                ConfigReader.getProperty("survey.hospital"),
//                "10-01-2026",
//                "10-03-2026"
//        );
//
//        Assert.assertTrue(page.isResultDisplayed(), "Date search failed");
//    }
//}


package tests.report;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.report.SurveyRegistrationReportPage;
import utils.ConfigReader;
import utils.AlertConfirmationPopup;


public class SurveyRegistrationReportTest extends BaseTest {

    private SurveyRegistrationReportPage page;
	private AlertConfirmationPopup alertConfirmationPopup;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Sidebar Navigation
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openReport();
        sidebar.clickSurveyRegistration();

        page = new SurveyRegistrationReportPage(driver);
    }

    @Test
	public void verifySurveyRegistrationReport() throws InterruptedException {
	
	    String groupName = ConfigReader.getProperty("survey.group");
	    String hospitalName = ConfigReader.getProperty("survey.hospital");
	
	    // ✅ Select Group
	    page.selectGroup(groupName);
	
	    // 🔥 wait for hospital load
	    Thread.sleep(1500);
	
	    // ✅ Select Hospital
	    page.selectHospital(hospitalName);
	
	    // ✅ Click Search
	    page.clickSearch();
	
	    // 🔥 Handle Alert IMMEDIATELY
	    boolean popup = page.handleNoRecordPopup();

	    if (popup) {
	        Assert.fail("❌ No records found for selected filters");
	    }
	

	}
}