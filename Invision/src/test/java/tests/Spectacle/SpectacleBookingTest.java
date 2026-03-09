package tests.Spectacle;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.Spectacle.SpectacleBookingPage;
import utils.ConfigReader;

public class SpectacleBookingTest extends BaseTest {

    private SpectacleBookingPage openSpectacleBookingPage() {
        LoginPage login = new LoginPage(driver);
        login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickSpectacle();
        sidebar.clickSpectacle(); // Ultra-stable submenu click

        return new SpectacleBookingPage(driver);
    }

    @Test
    public void searchByRegistrationNumber() {
        SpectacleBookingPage page = openSpectacleBookingPage();
        page.searchByRegistration("IH-IVC-26-0390");
        page.clickSearch();
        assert page.isResultPresent() : "No results found for Registration search";
    }

    @Test
    public void searchByPatientName() {
        SpectacleBookingPage page = openSpectacleBookingPage();
        page.searchByName("Rahul");
        page.clickSearch();
        assert page.isResultPresent() : "No results found for Name search";
    }

    @Test
    public void searchByPhoneNumber() {
        SpectacleBookingPage page = openSpectacleBookingPage();
        page.searchByPhone("9876543210");
        page.clickSearch();
        assert page.isResultPresent() : "No results found for Phone search";
    }

    @Test
    public void searchBySpectacleStatus() {
        SpectacleBookingPage page = openSpectacleBookingPage();
        page.selectSpectacleStatus("Prescribed");
        page.clickSearch();
        assert page.isResultPresent() : "No results found for Spectacle Status search";
    }

    @Test
    public void advanceSearchWithAllFilters() {
        SpectacleBookingPage page = openSpectacleBookingPage();
        page.performAdvanceSearch(
            "Delivered", "IH-IVC-26-0390", "Rahul", "9876543210",
            true, "09-03-2026", "09-03-2026"
        );
        assert page.isResultPresent() : "No results found for advance search with all filters";
    }

    @Test
    public void advanceSearchWithDateRangeOnly() {
        SpectacleBookingPage page = openSpectacleBookingPage();
        page.performAdvanceSearch("", "", "", "", true, "07-02-2026", "07-03-2026");
        assert page.isResultPresent() : "No results found for advance search by date range";
    }
}