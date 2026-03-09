
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By usernameField = By.cssSelector("input[name='loginModel.Username']");
    private By passwordField = By.cssSelector("input[name='loginModel.Password']");
    private By captchaField = By.cssSelector("input[placeholder='Captcha']");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");


    public void login(String username, String password) {

        // Wait until username field visible (page loaded)
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));

        enterText(usernameField, username);
        enterText(passwordField, password);

        System.out.println("Enter CAPTCHA manually...");

        // Wait until captcha is entered
        wait.until(driver ->
                driver.findElement(captchaField)
                        .getAttribute("value")
                        .length() > 0
        );

        safeClick(loginButton);

        waitForUrlContains("adminDashboard");
    }


    // Stable text entry
    private void enterText(By locator, String text) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                WebElement element = wait.until(
                        ExpectedConditions.elementToBeClickable(locator));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView(true);", element);

                element.clear();
                element.sendKeys(text);

                return;

            } catch (StaleElementReferenceException e) {

                attempts++;
            }
        }

        throw new RuntimeException("Unable to enter text into element: " + locator);
    }


    // Stable click
    private void safeClick(By locator) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                WebElement element = wait.until(
                        ExpectedConditions.elementToBeClickable(locator));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView(true);", element);

                element.click();

                return;

            } catch (StaleElementReferenceException e) {

                attempts++;
            }
        }

        throw new RuntimeException("Unable to click element: " + locator);
    }
}