//package tests;
//
//import base.BaseTest;
//import org.testng.annotations.Test;
//import pages.LoginPage;
//import pages.SidebarPage;
//import pages.PatientTypePage;
//import utils.ConfigReader;
//
//public class WalkInRegistrationTest extends BaseTest {
//
//    @Test
//    public void walkInRegistration() {
//
//        LoginPage login = new LoginPage(driver);
//        login.login(
//                ConfigReader.getProperty("username"),
//                ConfigReader.getProperty("password")
//        );
//
//        SidebarPage sidebar = new SidebarPage(driver);
//        sidebar.clickVisionCenter();
//        sidebar.clickRegistration();
//
//        PatientTypePage patientType = new PatientTypePage(driver);
//        patientType.selectWalkIn();
//
//        System.out.println("Walk-In Patient Type Selected");
//    }
//}


package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.PatientTypePage;
import pages.SidebarPage;
import pages.WalkInRegistrationPage;
import utils.ConfigReader;

public class WalkInRegistrationTest extends BaseTest {

	@Test
	public void walkInRegistration() {

	    // Login
	    LoginPage login = new LoginPage(driver);
	    login.login(
	            ConfigReader.getProperty("username"),
	            ConfigReader.getProperty("password")
	    );

	    // Sidebar Navigation
	    SidebarPage sidebar = new SidebarPage(driver);
	    sidebar.clickVisionCenter();
	    sidebar.clickRegistration();

	    // Select Patient Type
	    PatientTypePage pt = new PatientTypePage(driver);
	    pt.selectWalkIn();

	    // Fill Walk-In Registration
	    WalkInRegistrationPage reg = new WalkInRegistrationPage(driver);
	    reg.registerWalkInPatient();
	}

}
