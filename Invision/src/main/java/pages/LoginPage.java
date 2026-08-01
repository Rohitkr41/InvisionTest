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

    // ======================
    // LOCATORS
    // ======================

    private By usernameField = By.name("loginModel.Username");
    private By passwordField = By.cssSelector("input[name='loginModel.Password']");
    private By captchaField = By.cssSelector("input[placeholder='Captcha']");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");

    // ======================
    // LOGIN METHOD
    // ======================

   public void login(String username, String password) {

    System.out.println("\n======================================");
    System.out.println("LOGIN METHOD CALLED");
    System.out.println("Current URL : " + driver.getCurrentUrl());
    System.out.println("Thread ID   : " + Thread.currentThread().getId());
    System.out.println("======================================");

    // Wait until page fully loads
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));

    // Enter Username
    enterText(usernameField, username);

    WebElement usernameElement = driver.findElement(usernameField);
    System.out.println("Username after entry : "
            + usernameElement.getAttribute("value"));

    // Enter Password
    enterText(passwordField, password);

    WebElement passwordElement = driver.findElement(passwordField);
    System.out.println("Password length : "
            + passwordElement.getAttribute("value").length());

    System.out.println("Enter CAPTCHA manually...");

    // Wait until user enters captcha
    wait.until(driver -> {
        String captchaValue =
                driver.findElement(captchaField).getAttribute("value");

        return captchaValue != null && !captchaValue.trim().isEmpty();
    });

    System.out.println("CAPTCHA Entered Successfully");

    safeClick(loginButton);

    System.out.println("Login button clicked.");

    // Wait until dashboard loads
//    waitForUrlContains("adminDashboard");
    waitForUrlContains("Dashboard");

    System.out.println("Dashboard Loaded Successfully.");
}

    // ======================
    // STABLE TEXT ENTRY
    // ======================

  private void enterText(By locator, String text) {

    int attempts = 0;

    while (attempts < 3) {

        try {

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            wait.until(ExpectedConditions.elementToBeClickable(element));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

            System.out.println("------------------------------------------------");
            System.out.println("Typing into : " + locator);
            System.out.println("Text        : " + text);

            element.clear();
            element.sendKeys(text);

            // Debug logs
            System.out.println("Immediately : " + element.getAttribute("value"));

            Thread.sleep(3000);

            System.out.println("After 3 sec : " + element.getAttribute("value"));
            System.out.println("------------------------------------------------");

            return;

        } catch (StaleElementReferenceException e) {

            attempts++;

            if (attempts == 3) {
                throw new RuntimeException("Unable to enter text into element: " + locator);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    // ======================
    // STABLE CLICK
    // ======================

    private void safeClick(By locator) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                WebElement element = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(locator));

                wait.until(ExpectedConditions.elementToBeClickable(element));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

                try {
                    element.click();
                } catch (Exception e) {
                    // JS fallback click
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", element);
                }

                return;

            } catch (StaleElementReferenceException e) {

                attempts++;

                if (attempts == 3) {
                    throw new RuntimeException("Unable to click element: " + locator);
                }
            }
        }
    }
}
