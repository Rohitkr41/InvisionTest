//
//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class SidebarPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public SidebarPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//    }
//
//    // Locators
//    By visionCenterMenu = By.xpath("//span[contains(text(),'Vision Center')]");
//    By registrationMenu = By.xpath("//span[text()='Registration']");
//    By patientReceiptMenu = By.xpath("//span[.='Print Reciept']");
//    By EyeExaminationMenu = By.xpath("//span[.='Eye Examination']");
//
//    // Click Vision Center
//    public void clickVisionCenter() {
//        wait.until(ExpectedConditions.elementToBeClickable(visionCenterMenu)).click();
//    }
//
//    // Click Registration
//    public void clickRegistration() {
//        wait.until(ExpectedConditions.elementToBeClickable(registrationMenu)).click();
//    }
//
//	public void clickPatientReceipt() {
//		// TODO Auto-generated method stub
//		 wait.until(ExpectedConditions.elementToBeClickable(patientReceiptMenu)).click();
//	}
//
//	public void openEyeExamination() {
//		// TODO Auto-generated method stub
//		wait.until(ExpectedConditions.elementToBeClickable(EyeExaminationMenu)).click();
//	}
//}


package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidebarPage {

    WebDriver driver;
    WebDriverWait wait;

    public SidebarPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======================
    // LOCATORS
    // ======================

    By visionCenterMenu = By.xpath("//span[contains(text(),'Vision Center')]");
    By registrationMenu = By.xpath("//span[text()='Registration']");
	By patientReceiptMenu = By.xpath("//span[.='Print Reciept']");
	By EyeExaminationMenu = By.xpath("//span[.='Eye Examination']");

    // ======================
    // CLICK VISION CENTER
    // ======================

    public void clickVisionCenter() {

        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(visionCenterMenu));

        try {

            menu.click();

        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", menu);
        }
    }

    // ======================
    // CLICK REGISTRATION
    // ======================

    public void clickRegistration() {

        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(registrationMenu));

        menu.click();
    }

    // ======================
    // CLICK PRINT RECEIPT
    // ======================

    public void clickPatientReceipt() {

        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(patientReceiptMenu));

        menu.click();
    }

    // ======================
    // OPEN EYE EXAMINATION
    // ======================

    public void openEyeExamination() {

        // ensure Vision Center is open
        clickVisionCenter();

        WebElement eyeExam = wait.until(
                ExpectedConditions.elementToBeClickable(EyeExaminationMenu));

        try {

            eyeExam.click();

        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", eyeExam);
        }
    }
}
