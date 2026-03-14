package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class EyeExaminationActionPage extends BasePage {

    public EyeExaminationActionPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }

    // =============================
    // PAGE HEADER
    // =============================

    By pageHeader = By.xpath("//h4[.='Eye Examination']");

    // =============================
    // ADVANCE SEARCH
    // =============================

    By advanceSearchBtn = By.xpath("//form//a[2]");
    By fromDate = By.name("fromDatePres");
    By toDate = By.name("toDatePres");

    By searchBtn = By.xpath("//form//a[contains(text(),'Search')]");

    // =============================
    // RESULT TABLE
    // =============================

    By resultRow = By.xpath("//*[@id='h-din']//tbody//tr");

    // =============================
    // PLUS ICON (FIRST ROW ACTION)
    // =============================

    By plusIcon = By.xpath("//*[@id='h-din']//tbody//tr[1]//td[10]//i[1]");

    // =============================
    // SAFE CLICK
    // =============================

    public void safeClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        try {
            element.click();
        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    // =============================
    // OPEN ADVANCE SEARCH
    // =============================

    public void openAdvanceSearch() {

        safeClick(advanceSearchBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));
    }

    // =============================
    // SET DATE
    // =============================

    public void setDate(String from, String to) {

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
    // SEARCH BY DATE
    // =============================

    public void searchByDate(String from, String to) {

        openAdvanceSearch();
        setDate(from, to);

        safeClick(searchBtn);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRow, 0));
    }

    // =============================
    // CLICK FIRST ROW PLUS ICON
    // =============================

    public void clickFirstRowPlusIcon() {

        // wait for table rows
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRow, 0));

        // locator
        By plusIcon = By.xpath("//*[@id='h-din']//tbody//tr[1]//td[10]//i");

        // wait until visible
        WebElement icon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plusIcon));

        // scroll
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", icon);

        // small stability wait
        wait.until(ExpectedConditions.elementToBeClickable(icon));

        try {
            icon.click();
        } 
        catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", icon);
        }
    }
}