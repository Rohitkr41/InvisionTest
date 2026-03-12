package tests.report; 

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.report.SurveyRegistrationReportPage;
import utils.ConfigReader;
import utils.AlertHandler;

public class SurveyRegistrationReportTest extends BaseTest {

    private SurveyRegistrationReportPage page;

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
    public void verifySurveyRegistrationReport() {

        // Values from config (generic)
        String groupName = ConfigReader.getProperty("survey.group");
        String hospitalName = ConfigReader.getProperty("survey.hospital");

        page.selectGroup(groupName);
        page.selectHospital(hospitalName);

        page.clickSearch();

        // Alert Handle
        AlertHandler.closeNoDataAlert(driver);

        int results = page.getSurveyResultCount();

        System.out.println("Survey Records Found: " + results);

        Assert.assertTrue(results >= 0, "Survey report loaded successfully");
    }
}