package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class ComplaintOcularPage extends BasePage {

    public ComplaintOcularPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================

    By complaintMenu = By.xpath("//span[contains(text(),'Complaint')]");

    // =============================
    // CHIEF COMPLAINT
    // =============================

    By chiefComplaintField = By.xpath("//input[@name='chiefComplaint']");
    By eyeRE = By.xpath("//label[contains(text(),'RE')]");
    By periodField = By.xpath("//input[@name='period']");
    By durationDropdown = By.xpath("//select[@name='duration']");
    By saveChiefComplaint = By.xpath("//button[contains(text(),'Save')]");

    // =============================
    // OCULAR HISTORY
    // =============================

    By ocularHistoryField = By.xpath("//input[@name='ocularHistory']");
    By ocularEyeRE = By.xpath("(//label[contains(text(),'RE')])[2]");
    By previousTreatment = By.xpath("//input[@name='previousTreatment']");
    By remarksField = By.xpath("//input[@name='remarks']");
    By saveOcularHistory = By.xpath("(//button[contains(text(),'Save')])[2]");

    // =============================
    // ACTION METHODS
    // =============================

    public void clickComplaintMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(complaintMenu)).click();
    }

    public void addChiefComplaint() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(chiefComplaintField))
                .sendKeys("Eye Pain");

        driver.findElement(eyeRE).click();

        driver.findElement(periodField).sendKeys("2");

        driver.findElement(durationDropdown).sendKeys("Days");

        driver.findElement(saveChiefComplaint).click();
    }

    public void addOcularHistory() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(ocularHistoryField))
                .sendKeys("Blurred Vision");

        driver.findElement(ocularEyeRE).click();

        driver.findElement(previousTreatment).sendKeys("Eye Drops");

        driver.findElement(remarksField).sendKeys("No major issue");

        driver.findElement(saveOcularHistory).click();
    }
}