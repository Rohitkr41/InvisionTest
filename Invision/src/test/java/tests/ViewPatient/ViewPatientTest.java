package tests.ViewPatient;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.ViewPatient.ViewPatientPage;
import utils.ConfigReader;

public class ViewPatientTest extends BaseTest {

    // Common Login + Navigation
    public ViewPatientPage openViewPatientPage() {

        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();

        ViewPatientPage page = new ViewPatientPage(driver);
        page.clickViewPatient();

        return page;
    }


    // ==================================
    // Search By Registration Type
    // ==================================

    @Test
    public void searchByRegistrationType() {

        ViewPatientPage page = openViewPatientPage();

        page.selectRegistrationType("Walk-In/New");

        page.clickSearch();
    }


    // ==================================
    // Search By Registration Number
    // ==================================

    @Test
    public void searchByRegistrationNumber() {

        ViewPatientPage page = openViewPatientPage();

        page.searchByRegistrationNo("IH-IVC-26-0393");

        page.clickSearch();
    }


    // ==================================
    // Advance Search - By Name
    // ==================================

    @Test
    public void searchPatientByName() {

        ViewPatientPage page = openViewPatientPage();

        page.clickAdvanceSearch();

        page.enterFirstName("SOPHYA");

        page.clickAdvanceSearchButton();
    }


    // ==================================
    // Advance Search - By Mobile
    // ==================================

    @Test
    public void searchPatientByMobile() {

        ViewPatientPage page = openViewPatientPage();

        page.clickAdvanceSearch();

        page.enterPhoneNumber("343643646342");

        page.clickAdvanceSearchButton();
    }


    // ==================================
    // Advance Search - By Village
    // ==================================

    @Test
    public void searchPatientByVillage() {

        ViewPatientPage page = openViewPatientPage();

        page.clickAdvanceSearch();

        page.enterVillage("Rampur");

        page.clickAdvanceSearchButton();
    }


    // ==================================
    // Advance Search - By Date Range
    // ==================================

    @Test
    public void searchPatientByDateRange() {

        ViewPatientPage page = openViewPatientPage();

        page.clickAdvanceSearch();

        page.selectDateRange("07-02-2026", "07-03-2026");

        page.clickAdvanceSearchButton();
    }

}
