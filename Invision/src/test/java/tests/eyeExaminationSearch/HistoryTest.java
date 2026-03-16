package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import pages.eyeExaminationSearch.HistoryPage;
import utils.ConfigReader;

public class HistoryTest extends BaseTest {

    private EyeExaminationActionPage actionPage;
    private HistoryPage historyPage;

    @BeforeMethod
    public void setupPage() {

        // =============================
        // LOGIN
        // =============================
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // =============================
        // OPEN EYE EXAMINATION MODULE
        // =============================
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openEyeExamination();

        // =============================
        // INITIALIZE PAGE OBJECTS
        // =============================
        actionPage = new EyeExaminationActionPage(driver);
        historyPage = new HistoryPage(driver);

        // =============================
        // SEARCH PATIENT AND OPEN FIRST ROW
        // =============================
        actionPage.searchByDate("01-03-2026", "15-03-2026");
        actionPage.clickFirstRowPlusIcon();
    }

    @Test
    public void testHistory() {

        // =============================
        // OPEN HISTORY TAB
        // =============================
        historyPage.clickHistoryMenu();

        // =============================
        // ADD SYSTEMIC HISTORY
        // =============================
        historyPage.addSystemicHistory(
                "Hypertension",  // Systemic History
                "5",             // Period
                "Year",          // Duration
                "Under medication", // Remarks
                true             // Controlled? true = controlled, false = uncontrolled
        );

        // =============================
        // ADD FAMILY HISTORY
        // =============================
        historyPage.addFamilyHistory(
                "Glaucoma",      // Family History
                "Wife",        // Relation
                "Hereditary case" // Remarks
        );
    }
}

