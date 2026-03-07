
package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import pages.LoginPage;
import pages.PatientTypePage;
import pages.SidebarPage;
import pages.WalkInRegistrationPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

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
	
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) {

	    if (ITestResult.FAILURE == result.getStatus()) {

	        ScreenshotUtil.captureScreenshot(driver, result.getName());

	    }
	    
	 // Error Message Validation
        String errorMsg = driver.findElement(By.xpath("(//*[@id='main']//span)[2]")).getText();

        Assert.assertNotEquals(errorMsg, "Please select patient type.");
	}

}
