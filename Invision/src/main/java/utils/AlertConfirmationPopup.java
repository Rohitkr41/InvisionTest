package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertConfirmationPopup {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public AlertConfirmationPopup(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Generic Success Popup Handler
    public String handleSuccessPopup() {

        // Alert message
        By alertMessage = By.xpath("//div[contains(@class,'alert') or contains(@class,'swal')]");

        // OK Button
        By okButton = By.xpath("//button[.='OK' or .='Ok' or .='ok']");

        // Wait for message
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();

        // Click OK if present
        try {
            wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        } catch (Exception e) {
            System.out.println("OK button not found or already closed");
        }

        return message;
    }
}
