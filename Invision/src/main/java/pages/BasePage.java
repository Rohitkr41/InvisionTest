
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
   
    public BasePage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Wait for element visibility
    protected void waitForVisibility(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for URL
    protected void waitForUrlContains(String text) {

        wait.until(ExpectedConditions.urlContains(text));
    }

    // Stale-safe Click
    protected void click(By locator) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(locator)
                )).click();

                break;

            } catch (StaleElementReferenceException e) {

                attempts++;
            }
        }
    }

    // Stale-safe Type
    protected void type(By locator, String text) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                WebElement element = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(locator)
                ));

                element.clear();
                element.sendKeys(text);

                break;

            } catch (StaleElementReferenceException e) {

                attempts++;
            }
        }
    }

    // Dropdown Select
    protected void selectDropdown(By locator, String visibleText) {

        wait.until(ExpectedConditions.elementToBeClickable(locator));

        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(visibleText);
    }
}

