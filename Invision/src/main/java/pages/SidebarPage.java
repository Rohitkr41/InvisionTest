
package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidebarPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SidebarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======================
    // LOCATORS
    // ======================

    private By visionCenterMenu       = By.xpath("//span[contains(text(),'Vision Center')]");
    private By registrationMenu       = By.xpath("//span[normalize-space()='Registration']");
    private By patientReceiptMenu     = By.xpath("//span[normalize-space()='Print Reciept']");
    private By eyeExaminationMenu     = By.xpath("//span[normalize-space()='Eye Examination']");

    private By spectacleMenu          = By.xpath("//span[normalize-space()='Spectacle']");
    private By spectacleBookingMenu   = By.xpath("//span[normalize-space()='Spectacle Booking']");

    // ======================
    // SAFE CLICK METHOD
    // ======================
    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // ======================
    // VISION CENTER MENU
    // ======================
    public void clickVisionCenter() {
        safeClick(visionCenterMenu);
    }

    public void clickRegistration() {
        safeClick(registrationMenu);
    }

    public void clickPatientReceipt() {
        safeClick(patientReceiptMenu);
    }

    public void openEyeExamination() {
        clickVisionCenter();
        safeClick(eyeExaminationMenu);
    }

    // ======================
    // SPECTACLE MENU
    // ======================
    public void clickSpectacle() {
        try {
            // Expand Spectacle main menu
            safeClick(spectacleMenu);

            // Wait for Booking submenu to appear
            WebElement booking = wait.until(ExpectedConditions.visibilityOfElementLocated(spectacleBookingMenu));
            wait.until(ExpectedConditions.elementToBeClickable(booking));

            // Click Booking submenu
            safeClick(spectacleBookingMenu);

        } catch (Exception e) {
            throw new RuntimeException("Unable to click Spectacle Booking menu: " + e.getMessage());
        }
    }
}
