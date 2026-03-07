package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PatientTypePage extends BasePage {

    public PatientTypePage(WebDriver driver) {
        super(driver);
    }

    // Patient Type dropdown
    By patientTypeDropdown = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]");

    // Options
    By walkInOption = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]/option[contains(text(),'Walk')]");
    By followUpOption = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]/option[contains(text(),'Follow')]");
    By referralOption = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]/option[contains(text(),'Referral')]");



    // ✅ Walk-In
    public void selectWalkIn() {

        wait.until(ExpectedConditions.elementToBeClickable(patientTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(walkInOption)).click();

        driver.findElement(By.tagName("body")).click(); // close dropdown
    }


    // ✅ FollowUp
    public void selectFollowUp() {

        wait.until(ExpectedConditions.elementToBeClickable(patientTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(followUpOption)).click();

        driver.findElement(By.tagName("body")).click();
    }


    // ✅ Referral
    public void selectReferral() {

        wait.until(ExpectedConditions.elementToBeClickable(patientTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(referralOption)).click();

        driver.findElement(By.tagName("body")).click();
    }
}



//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//public class PatientTypePage extends BasePage {
//
//    public PatientTypePage(WebDriver driver) {
//        super(driver);
//    }
//
//    By patientTypeDropdown = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]");
//    By walkInOption = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]/option[contains(text(),'Walk')]");
//
//    public void selectWalkIn() {
//
//        // open dropdown
//        wait.until(ExpectedConditions.elementToBeClickable(patientTypeDropdown)).click();
//
//        // select Walk-In
//        wait.until(ExpectedConditions.elementToBeClickable(walkInOption)).click();
//
//        // click outside to close dropdown
//        driver.findElement(By.tagName("body")).click();
//    }
//}


