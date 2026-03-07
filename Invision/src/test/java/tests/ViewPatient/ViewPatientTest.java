
package tests.ViewPatient;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.ViewPatient.ViewPatientPage;
import utils.ConfigReader;

public class ViewPatientTest extends BaseTest {

    // Common method (Login + Navigation)
    public ViewPatientPage openViewPatientPage() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Open Vision Center
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickVisionCenter();

        // Open View Patient
        ViewPatientPage viewPatient = new ViewPatientPage(driver);
        viewPatient.clickViewPatient();

        return viewPatient;
    }


    // 1️⃣ Search by Registration Type
    @Test
    public void searchByRegistrationType() {

        ViewPatientPage viewPatient = openViewPatientPage();

        viewPatient.selectWalkIn();   // or selectReferral() / selectFollowUp()

        viewPatient.clickSearch();
    }


    // 2️⃣ Search by Registration Number
    @Test
    public void searchByRegistrationNumber() {

        ViewPatientPage viewPatient = openViewPatientPage();

        viewPatient.searchByRegistrationNo("IH-IVC-26-0390");

        viewPatient.clickSearch();
    }


    // 3️⃣ Open Advance Search
    @Test
    public void openAdvanceSearch() {

        ViewPatientPage viewPatient = openViewPatientPage();

        viewPatient.clickAdvanceSearch();
    }
}


