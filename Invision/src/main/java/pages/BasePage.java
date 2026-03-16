package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    By modal = By.cssSelector(".custom-modal");

    // =========================
    // WAIT FOR VISIBILITY
    // =========================
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // =========================
    // WAIT FOR CLICKABLE
    // =========================
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // =========================
    // WAIT FOR URL
    // =========================
    protected void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    // =========================
    // SCROLL TO ELEMENT
    // =========================
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // =========================
    // SMART CLICK (SCROLL + STALE SAFE + JS FALLBACK)
    // =========================
   protected void click(By locator) {

    int attempts = 0;

    while (attempts < 3) {

        try {

            waitForLoaderToDisappear();   // ⭐ NEW

            WebElement element = wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(locator)
                    )
            );

            scrollToElement(element);

            try {
                element.click();
            } catch (Exception e) {

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", element);
            }

            return;

        } catch (StaleElementReferenceException e) {
            attempts++;
        }
    }
}


    // =========================
    // SMART TYPE (SCROLL + STALE SAFE)
    // =========================
    protected void type(By locator, String text) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                WebElement element = wait.until(
                        ExpectedConditions.refreshed(
                                ExpectedConditions.visibilityOfElementLocated(locator)
                        )
                );

                scrollToElement(element);

                element.clear();
                element.sendKeys(text);

                return;

            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    // =========================
    // DROPDOWN SELECT
    // =========================
    protected void selectDropdown(By locator, String visibleText) {

        WebElement element = waitForClickable(locator);

        scrollToElement(element);

        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }

    // =========================
    // GET TEXT
    // =========================
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // =========================
    // CHECK DISPLAYED
    // =========================
    protected boolean isDisplayed(By locator) {

        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // =========================
    // WAIT FOR MODAL TO DISAPPEAR
    // =========================
    protected void waitForModalToDisappear() {

        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.cssSelector(".custom-modal")
                    )
            );

        } catch (Exception ignored) {
        }
    }

    // =========================
    // CLOSE SUCCESS ALERT
    // =========================
    protected void closeSuccessAlert() {

        try {

            By successOkButton = By.xpath("//*[@id='main']//div[4]//div[2]/button");

            wait.until(ExpectedConditions.visibilityOfElementLocated(successOkButton));

            click(successOkButton);

            wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));

        } catch (Exception e) {

            System.out.println("No success alert present");

        }
    }

    // =========================
    // HANDLE ANY OPEN MODAL
    // =========================
    protected void waitUntilModalGone() {

        try {

            List<WebElement> modals = driver.findElements(modal);

            for (WebElement m : modals) {

                if (m.isDisplayed()) {

                    List<WebElement> okButtons =
                            m.findElements(By.xpath(".//button[normalize-space()='OK']"));

                    if (!okButtons.isEmpty()) {

                        ((JavascriptExecutor) driver)
                                .executeScript("arguments[0].click();", okButtons.get(0));

                        wait.until(ExpectedConditions.invisibilityOf(m));
                    }
                }
            }

        } catch (Exception ignored) {
        }
    }

    // =============================
    // AUTOCOMPLETE HANDLER
    // =============================
    protected void selectFromAutocomplete(By fieldLocator, String value) {

        waitUntilModalGone();

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fieldLocator)
        );

        scrollToElement(field);

        String existingValue = field.getAttribute("value");

        if (existingValue != null && !existingValue.trim().isEmpty()) {
            return;
        }

        field.clear();
        field.sendKeys(value);

        By suggestion = By.xpath("//li[contains(text(),'" + value + "')]");

        WebElement option = wait.until(
                ExpectedConditions.visibilityOfElementLocated(suggestion)
        );

        scrollToElement(option);

        try {
            option.click();
        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", option);
        }
    }

    // =============================
    // SELECT BY VISIBLE TEXT
    // =============================
    protected void selectByVisibleText(By locator, String text) {

        WebElement element = waitForVisibility(locator);

        scrollToElement(element);

        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    
 // =========================
 // WAIT FOR LOADER
 // =========================
 protected void waitForLoaderToDisappear() {

     try {

         By loader = By.cssSelector(".loader, .spinner, .loading");

         wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

     } catch (Exception e) {

         // ignore if loader not present
     }
 }

}
