package tests.report;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.report.SurveyRegistrationReportPage;
import utils.ConfigReader;

public class SurveyRegistrationReportTest extends BaseTest {

    private SurveyRegistrationReportPage page;

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
