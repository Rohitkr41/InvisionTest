
package tests.eyeExaminationSearch;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationSearchPage;
import utils.ConfigReader;

public class EyeExaminationSearchTest extends BaseTest {

    private EyeExaminationSearchPage page;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // NAVIGATION
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();
        sidebar.openEyeExamination();

        // PAGE OBJECT
        page = new EyeExaminationSearchPage(driver);
    }

    // ======================
    // TOP SEARCH - REGISTRATION
    // ======================

    @Test
    public void topSearchByRegistration() {

        page.searchByRegistration("IH-IVC-26-0391");
        page.clickTopSearch();

        Assert.assertTrue(page.isResultDisplayed(),
                "Top Search Registration failed");
    }

    // ======================
    // TOP SEARCH - NAME
    // ======================

    @Test
    public void topSearchByName() {

        page.searchByName("RUBEENA");
        page.clickTopSearch();

        Assert.assertTrue(page.isResultDisplayed(),
                "Top Search Name failed");
    }

    // ======================
    // TOP SEARCH - STATUS DROPDOWN
    // ======================

    @Test
    public void topSearchByStatus() {

        page.selectScreeningStatus("New");
        page.clickTopSearch();

        Assert.assertTrue(page.isResultDisplayed(),
                "Top Search Status failed");
    }

    // ======================
    // ADVANCE SEARCH - REGISTRATION
    // ======================

    @Test
    public void advanceSearchByRegistration() {

        page.openAdvanceSearch();

        page.advanceSearch(
                "IH-IVC-26-0391",
                null,
                null,
                null,
                null,
                null
        );

        Assert.assertTrue(page.isResultDisplayed(),
                "Advance Search Registration failed");
    }

    // ======================
    // ADVANCE SEARCH - NAME
    // ======================

    @Test
    public void advanceSearchByName() {

        page.openAdvanceSearch();

        page.advanceSearch(
                null,
                "RUBEENA",
                null,
                null,
                null,
                null
        );

        Assert.assertTrue(page.isResultDisplayed(),
                "Advance Search Name failed");
    }

    // ======================
    // ADVANCE SEARCH - DATE
    // ======================

    @Test
    public void advanceSearchByDate() {

        page.openAdvanceSearch();

        page.advanceSearch(
                null,
                null,
                null,
                null,
                "10-01-2026",
                "10-03-2026"
        );

        Assert.assertTrue(page.isResultDisplayed(),
                "Advance Search Date failed");
    }

    // ======================
    // ADVANCE SEARCH - DATE + PHONE
    // ======================

    @Test
    public void advanceSearchByDateAndPhone() {

        page.openAdvanceSearch();

        page.advanceSearch(
                null,
                null,
                null,
                "1234567891",
                "10-01-2026",
                "10-03-2026"
        );

        Assert.assertTrue(page.isResultDisplayed(),
                "Advance Search Date + Phone failed");
    }
}
