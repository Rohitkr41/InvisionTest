package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import pages.eyeExaminationSearch.VisualAcuityRefractionPage;
import utils.ConfigReader;

public class VisualAcuityRefractionTest extends BaseTest {

    private EyeExaminationActionPage actionPage;
    private VisualAcuityRefractionPage visualPage;

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
        visualPage = new VisualAcuityRefractionPage(driver);

        // SEARCH PATIENT
        actionPage.searchByDate("22-06-2026", "30-06-2026");

        // OPEN PATIENT
        actionPage.clickFirstRowPlusIcon();
    }

    @Test
    public void testVisualAcuityRefraction() throws InterruptedException {

        visualPage.clickVisualAcuityMenu();
        visualPage.addVisualAcuity();
        visualPage.addRefraction();
    }
}
