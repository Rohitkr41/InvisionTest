package pages.eyeExaminationSearch;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import pages.BasePage;

public class EyeExaminationSearchPage extends BasePage {

    public EyeExaminationSearchPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LOCATORS
    // =============================

    private By advanceSearchIcon = By.xpath("//*[@id='bbssss']/i");

    private By advanceModal = By.xpath("//div[contains(@class,'modal-content')]");

    private By fromDate = By.name("fromDatePres");

    private By toDate = By.name("toDatePres");

    private By phoneNumber = By.name("inputValueofPhoneNo");

    private By screeningStatus = By.id("inputGroupSelect01");

    private By searchBtn = By.xpath("//a[normalize-space()='Search']");

    private By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");

    private By resultRows = By.xpath("//table/tbody/tr");

    private By alertOk = By.xpath("//button[normalize-space()='OK']");


    // =============================
    // ALERT HANDLER
    // =============================

    public void closeAlertIfPresent() {

        try {

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement okBtn = shortWait.until(
                    ExpectedConditions.elementToBeClickable(alertOk));

            okBtn.click();

            wait.until(ExpectedConditions.invisibilityOf(okBtn));

        } catch (TimeoutException e) {
            // ignore if popup not present
        }
    }


    // =============================
    // OPEN ADVANCE SEARCH
    // =============================

    public void openAdvanceSearch() {

        closeAlertIfPresent();

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(advanceSearchIcon));

        scrollToElement(btn);
        safeClick(btn);

        // wait for modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(advanceModal));

        // wait for filters inside modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));
    }


    // =============================
    // FILTER METHODS
    // =============================

    public void enterPhoneNumber(String phone) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(phoneNumber));

        field.clear();
        field.sendKeys(phone);
    }


    // Stable date selection (works with JS datepickers)
    public void selectDateRange(String from, String to) {

        WebElement fromField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromDate));

        WebElement toField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toDate));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].value='" + from + "';", fromField);
        js.executeScript("arguments[0].value='" + to + "';", toField);
    }


    public void selectScreeningStatus(String status) {

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(screeningStatus));

        Select select = new Select(dropdown);

        select.selectByVisibleText(status);
    }


    // =============================
    // CLICK SEARCH
    // =============================

    public void clickSearch() {

        closeAlertIfPresent();

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(searchBtn));

        scrollToElement(btn);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            btn.click();

        } catch (Exception e) {

            js.executeScript("arguments[0].click();", btn);
        }

        waitAfterSearch();
    }


    public void clickCancel() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(cancelBtn));

        safeClick(btn);
    }


    // =============================
    // WAIT AFTER SEARCH
    // =============================

    private void waitAfterSearch() {

        try {

            wait.until(ExpectedConditions.or(

                    ExpectedConditions.presenceOfElementLocated(resultRows),

                    ExpectedConditions.presenceOfElementLocated(alertOk)

            ));

        } catch (Exception e) {}

        closeAlertIfPresent();
    }


    // =============================
    // RESULT CHECK
    // =============================

    public boolean isResultPresent() {

        List<WebElement> rows = driver.findElements(resultRows);

        return rows.size() > 0;
    }


    // =============================
    // UTIL METHODS
    // =============================

    private void scrollToElement(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }


    private void safeClick(WebElement element) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                element.click();
                return;

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {

                attempts++;

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", element);
                return;
            }
        }

        throw new RuntimeException("Unable to click element");
    }
}