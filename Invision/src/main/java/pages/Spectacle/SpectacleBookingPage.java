package pages.Spectacle;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import utils.AlertHandler;

public class SpectacleBookingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SpectacleBookingPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class);
    }

    // ======================
    // LOCATORS
    // ======================

    private By registrationSearch = By.xpath("//input[contains(@placeholder,'registration')]");

    // SAME locator for Top + Advance
    private By patientName = By.xpath("//input[contains(@placeholder,'name')]");

    // Phone only in Advance Search
    private By phoneSearch = By.xpath("//input[@name='inputValueofPhoneNo']");

    private By spectacleStatusDropdown = By.id("inputGroupSelect01");

    // TOP SEARCH BUTTON
    private By topSearchBtn = By.xpath("//*[@id='top-headings']/div[2]/div/div[3]/form/a[2]/img");

    // ADVANCE SEARCH BUTTON
    private By advanceSearchBtn = By.xpath("//a[contains(text(),'Search')]");

    private By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");

    private By advanceFilterBtn = By.xpath("//*[@id='bbssss']/i");

    private By modalHeader = By.xpath("//h5[contains(text(),'Search Spectacles')]");

    private By prescriptionDateCheckbox = By.xpath("//input[@type='checkbox']");
    private By fromDate = By.name("fromDatePres");
    private By toDate = By.name("toDatePres");

    // TABLE VALIDATION COMMENTED
    // private By resultRows = By.xpath("//table/tbody/tr");

    private By alertMessage = By.xpath("//p[contains(text(),'No records found')]");

    // ======================
    // SAFE METHODS
    // ======================

    private void type(By locator, String text) {

        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);

        el.clear();
        el.sendKeys(text);
    }

    private void click(By locator) {

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);

        try {
            el.click();
        } 
        catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", el);
        }
    }

    private void selectDropdownByVisibleText(By locator, String text) {

        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        try {

            Select select = new Select(el);
            select.selectByVisibleText(text);

        } catch (Exception e) {

            el.sendKeys(text + Keys.ENTER);
        }
    }

    // ======================
    // TOP SEARCH METHODS
    // ======================

    public void searchByRegistration(String regNo) {
        type(registrationSearch, regNo);
    }

    public void searchByPatientName(String name) {
        type(patientName, name);
    }

    public void selectSpectacleStatus(String status) {
        selectDropdownByVisibleText(spectacleStatusDropdown, status);
    }

    public void clickTopSearch() {

        AlertHandler.closeNoDataAlert(driver);

        click(topSearchBtn);

        waitAfterSearch();

        AlertHandler.closeNoDataAlert(driver);
    }

    // ======================
    // ADVANCE SEARCH
    // ======================

    public void openAdvanceSearch() {

        click(advanceFilterBtn);

        waitForModal();
    }

    public void enablePrescriptionDate() {

        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(prescriptionDateCheckbox));

        if (!checkbox.isSelected()) {

            click(prescriptionDateCheckbox);
        }
    }

    public void enterFromDate(String from) {
        type(fromDate, from);
    }

    public void enterToDate(String to) {
        type(toDate, to);
    }

    public void clickAdvanceSearch() {

        AlertHandler.closeNoDataAlert(driver);

        click(advanceSearchBtn);

        waitAfterSearch();

        AlertHandler.closeNoDataAlert(driver);
    }

    public void performAdvanceSearch(String status,
                                     String regNo,
                                     String name,
                                     String phone,
                                     boolean useDate,
                                     String from,
                                     String to) {

        openAdvanceSearch();

        if (status != null && !status.isEmpty())
            selectSpectacleStatus(status);

        if (regNo != null && !regNo.isEmpty())
            searchByRegistration(regNo);

        if (name != null && !name.isEmpty())
            searchByPatientName(name);

        if (phone != null && !phone.isEmpty())
            type(phoneSearch, phone);

        if (useDate) {

            enablePrescriptionDate();

            if (from != null && !from.isEmpty())
                enterFromDate(from);

            if (to != null && !to.isEmpty())
                enterToDate(to);
        }

        clickAdvanceSearch();
    }

    // ======================
    // WAITS
    // ======================

    private void waitForModal() {

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(modalHeader));

        wait.until(ExpectedConditions
                .elementToBeClickable(registrationSearch));
    }

    private void waitAfterSearch() {

        WebDriverWait shortWait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        try {

            shortWait.until(driver -> {

                boolean alertVisible =
                        driver.findElements(alertMessage).size() > 0;

                return alertVisible;
            });

        } catch (Exception ignored) {}
    }
}