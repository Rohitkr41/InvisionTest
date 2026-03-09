
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SidebarPage {

    WebDriver driver;
    WebDriverWait wait;

    public SidebarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By visionCenterMenu = By.xpath("//span[contains(text(),'Vision Center')]");
    By registrationMenu = By.xpath("//span[text()='Registration']");
    By patientReceiptMenu = By.xpath("//span[.='Print Reciept']");

    // Click Vision Center
    public void clickVisionCenter() {
        wait.until(ExpectedConditions.elementToBeClickable(visionCenterMenu)).click();
    }

    // Click Registration
    public void clickRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationMenu)).click();
    }

	public void clickPatientReceipt() {
		// TODO Auto-generated method stub
		 wait.until(ExpectedConditions.elementToBeClickable(patientReceiptMenu)).click();
	}
}