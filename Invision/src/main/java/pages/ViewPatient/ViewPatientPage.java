package pages.ViewPatient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;

public class ViewPatientPage extends BasePage {

    public ViewPatientPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // View Patient Menu
    // =============================

    private By viewPatientMenu = By.xpath("//span[normalize-space()='View Patient']");


    // =============================
    // Main Search Section
    // =============================

    private By registrationTypeDropdown = By.xpath("//select[contains(@class,'form-select')]");

    private By registrationNoField = By.name("inputvalofMedicalNo");

    private By searchBtn = By.xpath("//*[@id='top-headings']//form//a[1]//img");

    private By advanceSearchIcon = By.xpath("//form//a[2]//i");


    // =============================
    // Advance Search Popup
    // =============================

    private By fromDate = By.name("fromDate");
    private By toDate = By.name("toDate");

    private By patientFirstName = By.name("inputValOfPatientname");

    private By phoneNumber = By.name("inputValueofPhoneNo");

    private By village = By.name("selectedAreaforSearch.AreaName");

    private By advanceSearchBtn = By.xpath("//a[.='Search']");
    private By cancelBtn = By.xpath("//button[.='Cancel']");


    // =============================
    // Open View Patient Page
    // =============================

    public void clickViewPatient() {

        wait.until(ExpectedConditions.elementToBeClickable(viewPatientMenu)).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // =============================
    // Stable Registration Type Dropdown
    // =============================

    public void selectRegistrationType(String type) {

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(registrationTypeDropdown));

        wait.until(ExpectedConditions.elementToBeClickable(dropdown));

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


    // Quick Methods

    public void selectWalkIn() {
        selectRegistrationType("Walk-In/New");
    }

    public void selectFollowUp() {
        selectRegistrationType("Followup/Old");
    }

    public void selectReferral() {
        selectRegistrationType("Referral");
    }

    public void selectPostOp() {
        selectRegistrationType("Post-Op");
    }

    public void selectOutreachCamp() {
        selectRegistrationType("Outreach-Camp");
    }

    public void selectSchoolScreening() {
        selectRegistrationType("School-Screening");
    }


    // =============================
    // Search By Registration No
    // =============================

    public void searchByRegistrationNo(String regNo) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(registrationNoField));

        field.clear();
        field.sendKeys(regNo);
    }


    // =============================
    // Click Search
    // =============================

    public void clickSearch() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".custom-modal")));

        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", search);

        wait.until(ExpectedConditions.elementToBeClickable(search));

        try {
            search.click();
        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", search);
        }
    }


    // =============================
    // Open Advance Search
    // =============================

    public void clickAdvanceSearch() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(advanceSearchIcon));

        btn.click();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,300)");
    }


    // =============================
    // Advance Search Filters
    // =============================

    public void enterFirstName(String name) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(patientFirstName));

        field.clear();
        field.sendKeys(name);
    }


    public void enterPhoneNumber(String phone) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(phoneNumber));

        field.clear();
        field.sendKeys(phone);
    }


    public void enterVillage(String villageName) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(village));

        field.clear();
        field.sendKeys(villageName);
    }


    public void selectDateRange(String from, String to) {

        WebElement fromField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromDate));

        fromField.clear();
        fromField.sendKeys(from);

        WebElement toField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toDate));

        toField.clear();
        toField.sendKeys(to);
    }


    // =============================
    // Advance Search Button
    // =============================

    public void clickAdvanceSearchButton() {

        wait.until(ExpectedConditions.elementToBeClickable(advanceSearchBtn)).click();
    }


    public void clickCancel() {

        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }
}