//
//
//package pages.Spectacle;
//
//import java.time.Duration;
//import java.util.List;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//
//public class SpectacleBookingPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public SpectacleBookingPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        this.wait.ignoring(StaleElementReferenceException.class)
//                 .ignoring(NoSuchElementException.class);
//    }
//
//    // ======================
//    // LOCATORS
//    // ======================
//    private By registrationSearch = By.name("inputvalueofMedicalNO");
//    private By nameSearch = By.xpath("//input[@placeholder='Search by name...']");
//    private By phoneSearch = By.xpath("//input[@placeholder='Search by phone no...']");
//    private By spectacleStatusDropdown = By.xpath("//*[@id=\"inputGroupSelect01\"]");
//
//    private By advanceFilterBtn = By.xpath("//*[@id=\"bbssss\"]/i");
//    private By modalHeader = By.xpath("//h5[contains(text(),'Search Spectacles')]");
//    private By prescriptionDateCheckbox = By.xpath("//input[@type='checkbox']");
//    private By fromDate = By.name("fromDatePres");
//    private By toDate = By.name("toDatePres");
//
//    private By searchBtn = By.xpath("//a[.='Search']");
//    private By cancelBtn = By.xpath("//button[.='Cancel']");
//    private By resultRows = By.xpath("//table/tbody/tr");
//
//    // ======================
//    // ALERT LOCATORS
//    // ======================
//    private By alertMessage = By.xpath("//p[contains(text(),'No records found')]");
//    private By okButton = By.xpath("//p[contains(text(),'No records found')]/following::button[normalize-space()='OK'][1]");
//
//    // ======================
//    // SAFE METHODS
//    // ======================
//    private WebElement get(By locator) {
//        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//    }
//
//    private void type(By locator, String text) {
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        el.clear();
//        el.sendKeys(text);
//    }
//
//    private void click(By locator) {
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        try {
//            el.click();
//        } catch (Exception e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//        }
//    }
//
//    private void selectDropdownByVisibleText(By locator, String visibleText) {
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        Select select = new Select(el);
//        select.selectByVisibleText(visibleText);
//    }
//
//    // ======================
//    // ALERT HANDLER
//    // ======================
//    private void closeNoDataAlertIfPresent() {
//        try {
//            if (!driver.findElements(alertMessage).isEmpty()) {
//                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
//                WebElement okBtn = shortWait.until(ExpectedConditions.elementToBeClickable(okButton));
//                try { okBtn.click(); } 
//                catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn); }
//                shortWait.until(ExpectedConditions.invisibilityOf(okBtn));
//                System.out.println("[SpectacleBookingPage] Closed 'No Data' alert");
//            }
//        } catch (Exception e) {
//            System.out.println("[SpectacleBookingPage] No alert present or could not close: " + e.getMessage());
//        }
//    }
//
//    // ======================
//    // TOP SEARCH METHODS
//    // ======================
//    public void searchByRegistration(String regNo) { type(registrationSearch, regNo); }
//    public void searchByName(String name) { type(nameSearch, name); }
//    public void searchByPhone(String phone) { type(phoneSearch, phone); }
//    public void selectSpectacleStatus(String status) { selectDropdownByVisibleText(spectacleStatusDropdown, status); }
//
//    public void clickSearch() {
//        // Automatically handle any pre-existing alert
//        closeNoDataAlertIfPresent();
//
//        click(searchBtn);
//        waitAfterSearch();
//
//        // Automatically handle alert after search
//        closeNoDataAlertIfPresent();
//    }
//
//    // ======================
//    // ADVANCE SEARCH METHODS
//    // ======================
//    public void openAdvanceSearch() { click(advanceFilterBtn); waitForModal(); }
//
//    public void enablePrescriptionDate() {
//        WebElement checkbox = get(prescriptionDateCheckbox);
//        if (!checkbox.isSelected()) click(prescriptionDateCheckbox);
//    }
//
//    public void enterFromDate(String from) { type(fromDate, from); }
//    public void enterToDate(String to) { type(toDate, to); }
//    public void clickCancel() { click(cancelBtn); }
//
//    public void performAdvanceSearch(String spectacleStatus,
//                                     String regNo,
//                                     String name,
//                                     String phone,
//                                     boolean usePrescriptionDate,
//                                     String from,
//                                     String to) {
//
//        openAdvanceSearch();
//
//        if (spectacleStatus != null && !spectacleStatus.isEmpty()) selectSpectacleStatus(spectacleStatus);
//        if (regNo != null && !regNo.isEmpty()) searchByRegistration(regNo);
//        if (name != null && !name.isEmpty()) searchByName(name);
//        if (phone != null && !phone.isEmpty()) searchByPhone(phone);
//
//        if (usePrescriptionDate) {
//            enablePrescriptionDate();
//            if (from != null && !from.isEmpty()) enterFromDate(from);
//            if (to != null && !to.isEmpty()) enterToDate(to);
//        }
//
//        clickSearch(); // alert auto-handled
//    }
//
//    // ======================
//    // RESULT METHODS
//    // ======================
//    public boolean isResultPresent() {
//        try {
//            return driver.findElements(resultRows).size() > 0;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    // ======================
//    // WAIT METHODS
//    // ======================
//    private void waitForModal() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
//        wait.until(ExpectedConditions.elementToBeClickable(registrationSearch));
//    }
//
//    private void waitAfterSearch() {
//        try { wait.until(ExpectedConditions.presenceOfElementLocated(resultRows)); }
//        catch (Exception ignored) {}
//    }
//}

package pages.Spectacle;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SpectacleBookingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SpectacleBookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.wait.ignoring(StaleElementReferenceException.class)
                 .ignoring(NoSuchElementException.class);
    }

    // ======================
    // LOCATORS
    // ======================
    // Top search
    private By registrationSearch = By.xpath("//input[@placeholder='Search by registration no...']");
    private By nameSearch = By.xpath("//input[@placeholder='Search by name...']");
    private By phoneSearch = By.xpath("//input[@placeholder='Search by phone no...']");
    private By spectacleStatusDropdown = By.xpath("//*[@id=\"inputGroupSelect01\"]");

    // Advance search modal
    private By advanceFilterBtn = By.xpath("//*[@id=\"bbssss\"]/i");
    private By modalHeader = By.xpath("//h5[contains(text(),'Search Spectacles')]");
    private By prescriptionDateCheckbox = By.xpath("//input[@type='checkbox']");
    private By fromDate = By.name("fromDatePres");
    private By toDate = By.name("toDatePres");

    private By searchBtn = By.xpath("//a[.='Search']");
    private By cancelBtn = By.xpath("//button[.='Cancel']");
    private By resultRows = By.xpath("//table/tbody/tr");

    // Alert
    private By alertMessage = By.xpath("//p[contains(text(),'No records found')]");
    private By okButton = By.xpath("//p[contains(text(),'No records found')]/following::button[normalize-space()='OK'][1]");

    // ======================
    // SAFE METHODS
    // ======================
    private WebElement get(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        return el;
    }

    private void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        el.clear();
        el.sendKeys(text);
    }

    private void click(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    private void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            Select select = new Select(el);
            select.selectByVisibleText(visibleText);
        } catch (Exception e) {
            el.sendKeys(visibleText + Keys.ENTER);
        }
    }

    // ======================
    // ALERT HANDLER
    // ======================
    private void closeNoDataAlertIfPresent() {
        try {
            if (!driver.findElements(alertMessage).isEmpty()) {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement okBtn = shortWait.until(ExpectedConditions.elementToBeClickable(okButton));
                try { okBtn.click(); } 
                catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn); }
                shortWait.until(ExpectedConditions.invisibilityOf(okBtn));
            }
        } catch (Exception ignored) {}
    }

    // ======================
    // TOP SEARCH METHODS
    // ======================
    public void searchByRegistration(String regNo) { type(registrationSearch, regNo); }
    public void searchByName(String name) { type(nameSearch, name); }
    public void searchByPhone(String phone) { type(phoneSearch, phone); }
    public void selectSpectacleStatus(String status) { selectDropdownByVisibleText(spectacleStatusDropdown, status); }

    public void clickSearch() {
        closeNoDataAlertIfPresent();
        click(searchBtn);
        waitAfterSearch();
        closeNoDataAlertIfPresent();
    }

    // ======================
    // ADVANCE SEARCH METHODS
    // ======================
    public void openAdvanceSearch() {
        click(advanceFilterBtn);
        waitForModal();
    }

    public void enablePrescriptionDate() {
        WebElement checkbox = get(prescriptionDateCheckbox);
        if (!checkbox.isSelected()) click(prescriptionDateCheckbox);
    }

    public void enterFromDate(String from) { type(fromDate, from); }
    public void enterToDate(String to) { type(toDate, to); }
    public void clickCancel() { click(cancelBtn); }

    public void performAdvanceSearch(String spectacleStatus,
                                     String regNo,
                                     String name,
                                     String phone,
                                     boolean usePrescriptionDate,
                                     String from,
                                     String to) {

        openAdvanceSearch();

        if (spectacleStatus != null && !spectacleStatus.isEmpty()) selectSpectacleStatus(spectacleStatus);
        if (regNo != null && !regNo.isEmpty()) searchByRegistration(regNo);
        if (name != null && !name.isEmpty()) searchByName(name);
        if (phone != null && !phone.isEmpty()) searchByPhone(phone);

        if (usePrescriptionDate) {
            enablePrescriptionDate();
            if (from != null && !from.isEmpty()) enterFromDate(from);
            if (to != null && !to.isEmpty()) enterToDate(to);
        }

        clickSearch();
    }

    // ======================
    // RESULTS
    // ======================
    public boolean isResultPresent() {
        try { return driver.findElements(resultRows).size() > 0; }
        catch (Exception e) { return false; }
    }

    // ======================
    // WAIT METHODS
    // ======================
    private void waitForModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
        wait.until(ExpectedConditions.elementToBeClickable(registrationSearch));
    }

    private void waitAfterSearch() {
        try { wait.until(ExpectedConditions.presenceOfElementLocated(resultRows)); }
        catch (Exception ignored) {}
    }
}