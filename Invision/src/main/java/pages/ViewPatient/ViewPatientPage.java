//
//package pages.ViewPatient;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//
//import pages.BasePage;
//
//public class ViewPatientPage extends BasePage {
//
//    public ViewPatientPage(WebDriver driver) {
//        super(driver);
//    }
//
//    // View Patient menu
//    By viewPatientMenu = By.xpath("//span[normalize-space()='View Patient']");
//
//    // Registration Type dropdown
//    By registrationTypeDropdown = By.xpath("//select[contains(@class,'form-select')]");
//
//    // Enter Registration No
//    By registrationNoField = By.name("inputvalofMedicalNo");
//
//    // Search button
//    By searchBtn = By.xpath("//*[@id='top-headings']//form//a[1]//img");
//
//    // Advance Search button
//    By advanceSearchBtn = By.xpath("//*[@id='bbssss']/i");
//
//
//    // Click View Patient
//    public void clickViewPatient() {
//
//        wait.until(ExpectedConditions.elementToBeClickable(viewPatientMenu)).click();
//    }
//
//
//    // Generic Ultra Stable Method for Dropdown
//    public void selectRegistrationType(String type) {
//
//        WebElement dropdown = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(registrationTypeDropdown));
//
//        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
//
//        // wait until options load
//        wait.until(driver -> new Select(dropdown).getOptions().size() > 1);
//
//        int attempts = 0;
//
//        while (attempts < 3) {
//
//            try {
//
//                Select select = new Select(dropdown);
//
//                select.selectByVisibleText(type);
//
//                return;
//
//            } catch (Exception e) {
//
//                attempts++;
//
//                try {
//                    Thread.sleep(700);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        // JavaScript fallback
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        js.executeScript(
//                "var select = arguments[0];" +
//                "for(var i=0;i<select.options.length;i++){" +
//                " if(select.options[i].text=='" + type + "'){" +
//                " select.selectedIndex=i;" +
//                " select.dispatchEvent(new Event('change'));" +
//                " break;" +
//                " }" +
//                "}", dropdown);
//    }
//
//
//    // Walk-In Patient
//    public void selectWalkIn() {
//        selectRegistrationType("Walk-In/New");
//    }
//
//    // Follow-Up Patient
//    public void selectFollowUp() {
//        selectRegistrationType("Followup/Old");
//    }
//
//    // Referral Patient
//    public void selectReferral() {
//        selectRegistrationType("Referral");
//    }
//
//    // Post-Op
//    public void selectPostOp() {
//        selectRegistrationType("Post-Op");
//    }
//
//    // Outreach Camp
//    public void selectOutreachCamp() {
//        selectRegistrationType("Outreach-Camp");
//    }
//
//    // School Screening
//    public void selectSchoolScreening() {
//        selectRegistrationType("School-Screening");
//    }
//
//
//    // Enter Registration Number
//    public void searchByRegistrationNo(String regNo) {
//
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(registrationNoField));
//
//        field.clear();
//        field.sendKeys(regNo);
//    }
//
//
//    // Click Search
//    public void clickSearch() {
//
//        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
//    }
//
//
//    // Click Advance Search
//    public void clickAdvanceSearch() {
//
//        WebElement btn = wait.until(
//                ExpectedConditions.elementToBeClickable(advanceSearchBtn));
//
//        btn.click();
//    }
//}
//
//


package pages.ViewPatient;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;

public class ViewPatientPage extends BasePage {

    public ViewPatientPage(WebDriver driver) {
        super(driver);
    }

    // View Patient menu
    private By viewPatientMenu = By.xpath("//span[normalize-space()='View Patient']");

    // Registration Type dropdown
    private By registrationTypeDropdown = By.xpath("//select[contains(@class,'form-select')]");

    // Enter Registration No
    private By registrationNoField = By.name("inputvalofMedicalNo");

    // Search button
    private By searchBtn = By.xpath("//*[@id='top-headings']//form//a[1]//img");

    // Advance Search button
    private By advanceSearchBtn = By.xpath("//*[@id='bbssss']/i");


    // Click View Patient
    public void clickViewPatient() {

        wait.until(ExpectedConditions.elementToBeClickable(viewPatientMenu)).click();

        // small wait for page load
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Generic Stable Dropdown Method
    public void selectRegistrationType(String type) {

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(registrationTypeDropdown));

        wait.until(ExpectedConditions.elementToBeClickable(dropdown));

        // wait until options load
        wait.until(driver -> new Select(dropdown).getOptions().size() > 1);

        int attempts = 0;

        while (attempts < 3) {

            try {

                Select select = new Select(dropdown);
                select.selectByVisibleText(type);

                return;

            } catch (Exception e) {

                attempts++;

                try {
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // JavaScript fallback
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
        selectRegistrationType("Walk-In/New");
    }

    // Follow-Up Patient
    public void selectFollowUp() {
        selectRegistrationType("Followup/Old");
    }

    // Referral Patient
    public void selectReferral() {
        selectRegistrationType("Referral");
    }

    // Post-Op
    public void selectPostOp() {
        selectRegistrationType("Post-Op");
    }

    // Outreach Camp
    public void selectOutreachCamp() {
        selectRegistrationType("Outreach-Camp");
    }

    // School Screening
    public void selectSchoolScreening() {
        selectRegistrationType("School-Screening");
    }


    // Enter Registration Number
    public void searchByRegistrationNo(String regNo) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(registrationNoField));

        field.clear();
        field.sendKeys(regNo);
    }


    // Click Search
    public void clickSearch() {

        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }


    // Click Advance Search
    public void clickAdvanceSearch() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(advanceSearchBtn));

        btn.click();

        // wait so advance search UI visible
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // scroll to show UI
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,300)");
    }
}
