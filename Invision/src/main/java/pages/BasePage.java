package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // 🔥 Stale-safe click
    protected void click(By locator) {
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(locator)
        )).click();
    }

    // 🔥 Stale-safe type (FINAL FIX)
    protected void type(By locator, String text) {

        int attempts = 0;

        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(locator)
                )).clear();

                driver.findElement(locator).sendKeys(text);
                break;

            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    protected void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }
}