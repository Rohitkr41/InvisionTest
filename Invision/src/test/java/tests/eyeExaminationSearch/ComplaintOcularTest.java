package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import pages.eyeExaminationSearch.ComplaintOcularPage;
import utils.ConfigReader;

public class ComplaintOcularTest extends BaseTest {

    private EyeExaminationActionPage actionPage;
    private ComplaintOcularPage ocularPage;

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
        
        ocularPage = new ComplaintOcularPage(driver);

        // SEARCH AND OPEN PATIENT
        actionPage.searchByDate("20-06-2026", "30-06-2026");
        actionPage.clickFirstRowPlusIcon();
    }

    @Test
    public void testComplaintAndOcularHistory() {

        ocularPage.clickComplaintMenu();
        ocularPage.addChiefComplaint();
        ocularPage.addOcularHistory();
    }
}
