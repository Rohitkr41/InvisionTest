package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidebarPage {

    WebDriver driver;
    WebDriverWait wait;

    public SidebarPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======================
    // LOCATORS
    // ======================

    private By visionCenterMenu = By.xpath("//span[contains(text(),'Vision Center')]");
    private By registrationMenu = By.xpath("//span[normalize-space()='Registration']");
    private By patientReceiptMenu = By.xpath("//span[normalize-space()='Print Reciept']");
    private By eyeExaminationMenu = By.xpath("//span[normalize-space()='Eye Examination']");

    private By spectacleMenu = By.xpath("//span[contains(text(),'Spectacle')]");
    private By spectacleBookingMenu = By.xpath("//span[contains(text(),'Spectacle Booking')]");

    // ======================
    // COMMON SAFE CLICK
    // ======================

    private void safeClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        try {

            element.click();

        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    // ======================
    // VISION CENTER MENU
    // ======================

    public void clickVisionCenter() {
        safeClick(visionCenterMenu);
    }

    // ======================
    // REGISTRATION
    // ======================

    public void clickRegistration() {
        safeClick(registrationMenu);
    }

    // ======================
    // PRINT RECEIPT
    // ======================

    public void clickPatientReceipt() {
        safeClick(patientReceiptMenu);
    }

    // ======================
    // OPEN EYE EXAMINATION
    // ======================

    public void openEyeExamination() {

        clickVisionCenter();
        safeClick(eyeExaminationMenu);
    }

    // ======================
    // OPEN SPECTACLE BOOKING
    // ======================

    public void openSpectacleBooking() {

        safeClick(spectacleMenu);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(spectacleBookingMenu));

        safeClick(spectacleBookingMenu);
    }
}