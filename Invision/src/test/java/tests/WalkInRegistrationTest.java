
package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.PatientTypePage;
import pages.SidebarPage;
import pages.WalkInRegistrationPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class WalkInRegistrationTest extends BaseTest {

	@Test
	public void walkInRegistration() throws InterruptedException {

		// Login
		LoginPage login = new LoginPage(driver);
		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password")

		);

		int registrationCount = 4;

		for (int i = 1; i <= registrationCount; i++) {

			System.out.println("Registration Number : " + i);

			SidebarPage sidebar = new SidebarPage(driver);
//		    sidebar.clickVisionCenter();
			sidebar.clickCommunityclinc();
			sidebar.clickRegistration();

			PatientTypePage pt = new PatientTypePage(driver);
			pt.selectWalkIn();

			WalkInRegistrationPage reg = new WalkInRegistrationPage(driver);
			reg.registerWalkInPatient();

			// Registration complete hone ke baad refresh
			driver.navigate().refresh();

		}
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			ScreenshotUtil.captureScreenshot(driver, result.getName());

			if (driver.findElements(By.xpath("(//*[@id='main']//span)[2]")).size() > 0) {

				String error = driver.findElement(By.xpath("(//*[@id='main']//span)[2]")).getText();

				System.out.println(error);
			}
		}
	}

}
