package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import dev.failsafe.internal.util.Assert;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import pages.eyeExaminationSearch.EyeExaminationSearchPage;
import utils.ConfigReader;

public class EyeExaminationActionTest extends BaseTest {

    private EyeExaminationActionPage page;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN EYE EXAMINATION MODULE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openEyeExamination();

        // INITIALIZE PAGE OBJECT
        page = new EyeExaminationActionPage(driver);
    }

    @Test
    public void testAdvanceSearchAndClickPlus() {

        // SEARCH USING DATE FILTER
        page.searchByDate("13-03-2026", "15-03-2026");

        // CLICK FIRST ROW PLUS ICON
        page.clickFirstRowPlusIcon();

        // DEBUG (OPTIONAL)
        System.out.println("Plus icon clicked successfully");
    }

}
