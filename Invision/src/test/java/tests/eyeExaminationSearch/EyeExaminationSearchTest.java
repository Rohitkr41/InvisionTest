package tests.eyeExaminationSearch;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationSearchPage;
import utils.ConfigReader;

public class EyeExaminationSearchTest extends BaseTest {

    public EyeExaminationSearchPage openEyeExaminationPage() {

        LoginPage login = new LoginPage(driver);

        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        SidebarPage sidebar = new SidebarPage(driver);

        sidebar.clickVisionCenter();

        sidebar.openEyeExamination();

        EyeExaminationSearchPage page = new EyeExaminationSearchPage(driver);

        return page;
    }


    // ============================
    // Search By Phone
    // ============================

    @Test
    public void searchByPhone() {

        EyeExaminationSearchPage page = openEyeExaminationPage();

        page.openAdvanceSearch();

        page.enterPhoneNumber("9999999999");

        page.clickSearch();

        Assert.assertTrue(page.isResultPresent());
    }


    // ============================
    // Search By Date
    // ============================

    @Test
    public void searchByDate() {

        EyeExaminationSearchPage page = openEyeExaminationPage();

        page.openAdvanceSearch();

        page.selectDateRange("01-03-2026", "10-03-2026");

        page.clickSearch();

        Assert.assertTrue(page.isResultPresent());
    }


    // ============================
    // Search By Screening Status
    // ============================

    @Test
    public void searchByScreeningStatus() {

        EyeExaminationSearchPage page = openEyeExaminationPage();

        page.openAdvanceSearch();

        page.selectScreeningStatus("New");

        page.clickSearch();

        Assert.assertTrue(page.isResultPresent());
    }


    // ============================
    // Advance Search (Combined)
    // ============================

    @Test
    public void advanceSearchFilter() {

        EyeExaminationSearchPage page = openEyeExaminationPage();

        page.openAdvanceSearch();

        page.enterPhoneNumber("9999999999");

        page.selectDateRange("01-03-2026", "10-03-2026");

        page.selectScreeningStatus("In-Progress");

        page.clickSearch();

        Assert.assertTrue(page.isResultPresent());
    }

}
