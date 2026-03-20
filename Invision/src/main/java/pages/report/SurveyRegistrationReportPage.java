
package pages.report;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SurveyRegistrationReportPage {

    WebDriver driver;
    WebDriverWait wait;

    public SurveyRegistrationReportPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======================
    // LOCATORS
    // ======================

    private By groupDropdown = By.xpath("(//select[contains(@class,'search-input')])[1]");
    private By hospitalDropdown = By.xpath("(//select[contains(@class,'search-input')])[2]");
    private By searchButton = By.xpath("//*[@id='top-headings']/div[2]/div/form/a[1]/img");
    private By tableRows = By.xpath("//table//tbody//tr");

    // ======================
    // FINAL DROPDOWN HANDLER
    // ======================

   private void selectDropdown(By locator, String visibleText) {

    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    Select select = new Select(dropdown);

    wait.until(driver -> select.getOptions().size() > 1);

    boolean found = false;

    for (WebElement option : select.getOptions()) {

        String text = option.getText().trim();
        String value = option.getAttribute("value");

        System.out.println("TEXT: " + text + " | VALUE: " + value);

        if (text.equalsIgnoreCase(visibleText.trim())) {

            // 🔥 FINAL FIX: select by VALUE
            select.selectByValue(value);

            // 🔥 trigger change event (important)
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].dispatchEvent(new Event('change'))", dropdown);

            found = true;
            break;
        }
    }

    if (!found) {
        throw new RuntimeException("Dropdown value not found: " + visibleText);
    }
}

    // ======================
    // SELECT GROUP
    // ======================

   public void selectGroup(String groupName) {

	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(groupDropdown));

	    // 🔥 STEP 1: click dropdown (important)
	    dropdown.click();

	    Select select = new Select(dropdown);

	    // 🔥 STEP 2: select by visible text
	    select.selectByVisibleText(groupName);

	    // 🔥 STEP 3: force JS events (VERY IMPORTANT)
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", dropdown);
	    js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", dropdown);

	    // 🔥 STEP 4: blur (MOST IMPORTANT for validation)
	    js.executeScript("arguments[0].blur();", dropdown);

	    // 🔥 STEP 5: click outside (simulate real user)
	    driver.findElement(By.tagName("body")).click();

	    System.out.println("✅ Group selected: " + groupName);
	}

    // ======================
    // SELECT HOSPITAL
    // ======================

  public void selectHospital(String hospitalName) {

    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(hospitalDropdown));

    Select select = new Select(dropdown);

    wait.until(driver -> select.getOptions().size() > 1);

    boolean found = false;

    for (WebElement option : select.getOptions()) {

        String text = option.getText().trim();
        System.out.println("HOSPITAL OPTION: " + text);

        if (text.equalsIgnoreCase(hospitalName.trim())) {

            select.selectByVisibleText(text);

            // trigger JS events
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", dropdown);
            js.executeScript("arguments[0].blur();", dropdown);

            found = true;
            break;
        }
    }

    if (!found) {
        throw new RuntimeException("❌ Hospital NOT found: " + hospitalName);
    }
}

    // ======================
    // CLICK SEARCH
    // ======================

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean handleNoRecordPopup() {
        try {
            // 🔥 Wait for message
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'alert-body')]//p[contains(text(),'No records found')]")
            ));

            System.out.println("⚠️ Popup Message: " + message.getText());

            // 🔥 Click OK button
            WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'alert-btn')]//button[normalize-space()='OK']")
            ));

            okBtn.click();

            System.out.println("✅ Popup handled successfully");

            return true;

        } catch (Exception e) {
            return false;
        }
    }
  
}
