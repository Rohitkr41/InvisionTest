<<<<<<< HEAD
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
=======
package tests.report;
>>>>>>> 573403487f5bc32e7d3c682235c6e499b913b872


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
<<<<<<< HEAD
import utils.AlertConfirmationPopup;

=======
>>>>>>> 573403487f5bc32e7d3c682235c6e499b913b872

public class SurveyRegistrationReportTest extends BaseTest {

    private SurveyRegistrationReportPage page;
	private AlertConfirmationPopup alertConfirmationPopup;

    @BeforeMethod
    public void setupPage() {

        // 🔹 LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // 🔹 NAVIGATION
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openReport();
        sidebar.clickSurveyRegistration();

        page = new SurveyRegistrationReportPage(driver);

        // 🔥 IMPORTANT: Always select before every test
        page.selectGroup(ConfigReader.getProperty("survey.group"));
        page.selectHospital(ConfigReader.getProperty("survey.hospital"));
    }

    // ======================
    // TOP SEARCH
    // ======================

    @Test
<<<<<<< HEAD
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
=======
    public void topSearchTest() {

        page.clickTopSearch();

        if (page.handleNoRecordPopup()) {
            Assert.fail("❌ No records in Top Search");
        }
    }

    // ======================
    // ADVANCE - PATIENT NAME
    // ======================

    @Test
    public void advanceSearchByPatient() {

        page.openAdvanceSearch();
        page.enterPatientName(ConfigReader.getProperty("survey.patient"));
        page.clickAdvanceSearch();

        if (page.handleNoRecordPopup()) {
            Assert.fail("❌ No records for patient");
        }
    }

    // ======================
    // ADVANCE - PHONE
    // ======================

    @Test
    public void advanceSearchByPhone() {

        page.openAdvanceSearch();
        page.enterPhone(ConfigReader.getProperty("survey.phone"));
        page.clickAdvanceSearch();

        if (page.handleNoRecordPopup()) {
            Assert.fail("❌ No records for phone");
        }
    }

    // ======================
    // ADVANCE - DATE
    // ======================

    @Test
    public void advanceSearchByDate() {

        page.openAdvanceSearch();

        page.selectDateRange(
                ConfigReader.getProperty("survey.fromDate"),
                ConfigReader.getProperty("survey.toDate")
        );

        page.clickAdvanceSearch();

        if (page.handleNoRecordPopup()) {
            Assert.fail("❌ No records for date");
        }
    }

    // ======================
    // FULL FLOW
    // ======================

    @Test
    public void fullAdvanceSearch() {

        page.openAdvanceSearch();

        page.enterPatientName("rohit");
        page.enterPhone("5456456464");
        page.enterMemberNo("3434343");
        page.enterDoorNo("12A");
        page.enterAreaName("Delhi");
        page.enterScreenedBy("Dr Sharma");

        page.selectPatientType("Present");

        page.selectDateRange(
               "02-02-2026",
                "20-03-2026"
        );

        page.clickAdvanceSearch();

        if (page.handleNoRecordPopup()) {
            Assert.fail("❌ No records in full flow");
        }
    }
}
>>>>>>> 573403487f5bc32e7d3c682235c6e499b913b872
