package tests.Spectacle;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.Spectacle.SpectacleBookingPage;
import utils.ConfigReader;

public class SpectacleBookingTest extends BaseTest {

    private SpectacleBookingPage page;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        // OPEN SPECTACLE BOOKING
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openSpectacleBooking();

        // PAGE OBJECT
        page = new SpectacleBookingPage(driver);
    }

    // ======================
    // TOP SEARCH - REGISTRATION
    // ======================

    @Test
    public void searchByRegistrationNumber() {

        page.searchByRegistration("IH-IVC-26-0391");
        page.clickTopSearch();
    }

    // ======================
    // TOP SEARCH - PATIENT NAME
    // ======================

    @Test
    public void searchByPatientName() {

        page.searchByPatientName("Rahul");
        page.clickTopSearch();
    }

    // ======================
    // TOP SEARCH - STATUS
    // ======================

    @Test
    public void searchBySpectacleStatus() {

        page.selectSpectacleStatus("Prescribed");
        page.clickTopSearch();
    }

    // ======================
    // ADVANCE SEARCH - ALL FILTERS
    // ======================

    @Test
    public void advanceSearchWithAllFilters() {

        page.performAdvanceSearch(
                "Delivered",
                "IH-IVC-26-0390",
                "Rahul",
                "9876543210",
                true,
                "09-03-2026",
                "09-03-2026"
        );
    }

    // ======================
    // ADVANCE SEARCH - DATE RANGE
    // ======================

    @Test
    public void advanceSearchWithDateRangeOnly() {

        page.performAdvanceSearch(
                "",
                "",
                "",
                "",
                true,
                "07-02-2026",
                "07-03-2026"
        );
    }
}
