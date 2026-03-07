//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//
//public class PatientTypePage extends BasePage {
//
//    public PatientTypePage(WebDriver driver) {
//        super(driver);
//    }
//
//    // Patient Type Dropdown
//    By patientTypeDropdown = By.xpath("(//*[@id='main']//form//select)[1]");
//
//
//
//    // ✅ Walk-In Patient
//    public void selectWalkIn() {
//
//        wait.until(ExpectedConditions.elementToBeClickable(patientTypeDropdown));
//
//        Select patientType = new Select(driver.findElement(patientTypeDropdown));
//        patientType.selectByVisibleText("Walk-In/New");;
//    }
//    
//  
//
//
//
//    // ✅ Follow-Up Patient
//    public void selectFollowUp() {
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(patientTypeDropdown));
//
//        Select patientType = new Select(driver.findElement(patientTypeDropdown));
//        patientType.selectByVisibleText("Followup/Old");
//    }
//
//
//
//    // ✅ Referral Patient
//    public void selectReferral() {
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(patientTypeDropdown));
//
//        Select patientType = new Select(driver.findElement(patientTypeDropdown));
//        patientType.selectByVisibleText("Referral");
//    }
//}
//
//


package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PatientTypePage extends BasePage {

    public PatientTypePage(WebDriver driver) {
        super(driver);
    }

    // Patient Type Dropdown
    By patientTypeDropdown = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]");


    // Generic Method (Ultra Stable)
    public void selectPatientType(String type) {

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(patientTypeDropdown));

        wait.until(ExpectedConditions.elementToBeClickable(dropdown));

        // wait until dropdown options load
        wait.until(driver -> new Select(dropdown).getOptions().size() > 1);

        int attempts = 0;

        while (attempts < 3) {
            try {

                Select patientType = new Select(dropdown);
                patientType.selectByVisibleText(type);

                return; // success

            } catch (Exception e) {

                attempts++;

                try {
                    Thread.sleep(700); // small pause
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // JavaScript fallback (if Selenium fails)
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "var select = arguments[0];" +
                "for(var i=0;i<select.options.length;i++){" +
                " if(select.options[i].text=='" + type + "'){" +
                " select.selectedIndex=i;" +
                " select.dispatchEvent(new Event('change'));" +
                " break;" +
                " }" +
                "}", dropdown);
    }


    // Walk-In Patient
    public void selectWalkIn() {
        selectPatientType("Walk-In/New");
    }

    // Follow-Up Patient
    public void selectFollowUp() {
        selectPatientType("Followup/Old");
    }

    // Referral Patient
    public void selectReferral() {
        selectPatientType("Referral");
    }
}
