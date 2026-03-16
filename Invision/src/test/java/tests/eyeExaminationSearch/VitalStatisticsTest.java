package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import pages.eyeExaminationSearch.VitalStatisticsPage;
import utils.ConfigReader;

public class VitalStatisticsTest extends BaseTest {

    private EyeExaminationActionPage actionPage;
    private VitalStatisticsPage vitalPage;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN MODULE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openEyeExamination();

        // PAGE OBJECT
        actionPage = new EyeExaminationActionPage(driver);
        vitalPage = new VitalStatisticsPage(driver);

        // SEARCH PATIENT
        actionPage.searchByDate("01-03-2026", "15-03-2026");
        actionPage.clickFirstRowPlusIcon();
    }

    @Test
    public void testVitalStatistics() {

        vitalPage.clickVitalMenu();
        vitalPage.addVitalStatistics();   // already includes save
    }
}
