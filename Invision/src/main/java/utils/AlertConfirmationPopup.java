package utils;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertConfirmationPopup {

    WebDriver driver;
    WebDriverWait wait;

    public AlertConfirmationPopup(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String handlePopupFast() {
        String message = "";

        By alertMessage = By.xpath(
                "//div[contains(@class,'alert') and not(contains(@style,'display: none'))] | " +
                "//div[contains(@class,'swal2-popup')] | " +
                "//p[contains(text(),'successfully') or contains(text(),'exist') or contains(text(),'sure')]"
        );

        By okButton = By.xpath("(//button[normalize-space()='OK' or normalize-space()='Ok'])[3]");
        By yesButton = By.xpath("//button[normalize-space()='Yes']");

        try {
            WebElement msgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
            message = msgEl.getText();

            // Click OK
            try {
                WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(okButton));
                clickJS(okBtn);
            } catch (Exception ignored) {}

            // Click Yes
            try {
                WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(yesButton));
                clickJS(yesBtn);
            } catch (Exception ignored) {}

            // Wait for alert to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(alertMessage));

        } catch (Exception e) {
            // No alert found
        }

        return message;
    }

    private void clickJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
