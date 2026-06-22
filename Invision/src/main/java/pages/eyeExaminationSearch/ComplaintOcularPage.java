
package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class ComplaintOcularPage extends BasePage {

    public ComplaintOcularPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================
    By complaintMenu = By.xpath("//*[@id='side-box-nav']/li[2]/a");

    // =============================
    // CHIEF COMPLAINT
    // =============================
    private final By chiefComplaintField =
            By.xpath("//input[@data-dropdown='complaintDropdown']");

    private final By eyeRE =
            By.id("RM_rdbREs");

    private final By periodField =
            By.id("numberInput");

    private final By durationDropdown =
            By.xpath("//*[@id='box-main']//select");

    private final By saveChiefComplaint =
            By.id("RM_btnSubmit");

    // =============================
    // OCULAR HISTORY
    // =============================
    private final By ocularHistoryField =
            By.xpath("(//input[@data-dropdown='ocularDropdown'])[1]");

    private final By ocularEyeRE =
            By.id("RM_rdbRE");

    private final By previousTreatment =
            By.xpath("(//input[@data-dropdown='ocularDropdown'])[2]");

    private final By remarksField =
            By.xpath("//label[text()='Remarks']/following::input[1]");

    private final By saveOcularHistory =
            By.xpath("(//button[@id='RM_btnSubmit'])[2]");


    // =============================
    // ALERT / MODAL
    // =============================
    By chiefComplaintAlert = By.xpath("//*[contains(text(),'Chief Complaint already exist!')]");
    By modal = By.cssSelector(".custom-modal");

    // =============================
    // CLICK COMPLAINT MENU
    // =============================
    public void clickComplaintMenu() {
        waitUntilModalGone();
        wait.until(ExpectedConditions.elementToBeClickable(complaintMenu)).click();
    }

    // =============================
    // ULTRA STABLE AUTOCOMPLETE SELECT
    // =============================
    public void selectChiefComplaint(String complaint) {
        waitUntilModalGone();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(chiefComplaintField));
        field.clear();
        field.sendKeys(complaint);

        By suggestion = By.xpath("//li[contains(text(),'" + complaint + "')]");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(option)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
    }

    // =============================
    // ADD CHIEF COMPLAINT
    // =============================
    public void addChiefComplaint() {
        selectChiefComplaint("BLUR VISION");

        clickWhenModalGone(wait.until(ExpectedConditions.elementToBeClickable(eyeRE)));

        WebElement period = waitUntilModalGoneAndVisible(periodField);
        period.clear();
        period.sendKeys("2");

        driver.findElement(durationDropdown).sendKeys("Days");
        driver.findElement(saveChiefComplaint).click();

        waitUntilModalGone();
    }

  public void addOcularHistory() {

    System.out.println("Step 1 : Ocular History");

    selectFromDropdown(ocularHistoryField, "CATARACT");

    System.out.println("Step 2 : Eye Selection");

    safeClick(waitVisible(ocularEyeRE));

    System.out.println("Step 3 : Previous Treatment");

    selectFromDropdown(previousTreatment, "GLASSES");

    System.out.println("Step 4 : Remarks");

    WebElement remarks = waitVisible(remarksField);
    remarks.clear();
    remarks.sendKeys("No major issue");

    System.out.println("Step 5 : Save");

    WebElement save =
            wait.until(ExpectedConditions.elementToBeClickable(saveOcularHistory));

    safeClick(save);
    handleAnyPopup();
}
 
 
 private void selectFromDropdown(By inputLocator, String value) {

     WebElement input = waitVisible(inputLocator);

     input.click();
     input.clear();
     input.sendKeys(value);

     By suggestionList =
             By.xpath("//ul[contains(@class,'suggestions-list')]");

     wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionList));

     By option = By.xpath(
             "//li[contains(@class,'suggestion-item') and contains(normalize-space(),'"
                     + value + "')]");

     WebElement item =
             wait.until(ExpectedConditions.elementToBeClickable(option));

     safeClick(item);

     ((JavascriptExecutor) driver).executeScript(
             "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
             input);

     ((JavascriptExecutor) driver).executeScript(
             "arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
             input);
 }

 
 private WebElement waitVisible(By locator) {

     waitUntilModalGone();

     return wait.until(
             ExpectedConditions.visibilityOfElementLocated(locator));
 }

    // =============================
    // MODAL HANDLING
    // =============================
 
    private WebElement waitUntilModalGoneAndVisible(By locator) {
        waitUntilModalGone();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void clickWhenModalGone(WebElement element) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                waitUntilModalGone();
                element.click();
                return;
            } catch (Exception e) {
                try { Thread.sleep(200); } catch (Exception ignored) {}
            }
            attempts++;
        }
    }
    
    private void handleAnyPopup() {

        By alertMsg = By.xpath(
                "//div[contains(@class,'alert') and not(contains(@style,'display: none'))] | " +
                "//div[contains(@class,'swal2-popup')] | " +
                "//div[contains(@class,'toast')] | " +
                "//p[contains(text(),'successfully') or contains(text(),'exist') or contains(text(),'Ocular')]"
        );

        By okBtn = By.xpath("(//button[normalize-space()='OK' or normalize-space()='Ok'])[3]");
        By yesBtn = By.xpath("//button[normalize-space()='Yes']");

        for (int i = 0; i < 5; i++) {
            try {
                WebElement msg = driver.findElement(alertMsg);

                if (msg.isDisplayed()) {
                    System.out.println("✅ Popup Found: " + msg.getText());

                    // Try OK
                    try {
                        WebElement ok = driver.findElement(okBtn);
                        if (ok.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ok);
                            return;
                        }
                    } catch (Exception ignored) {}

                    // Try YES
                    try {
                        WebElement yes = driver.findElement(yesBtn);
                        if (yes.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yes);
                            return;
                        }
                    } catch (Exception ignored) {}

                    return;
                }

            } catch (Exception e) {
                try { Thread.sleep(400); } catch (Exception ignored) {}
            }
        }

        System.out.println("⚠️ No popup detected");
    }

}
