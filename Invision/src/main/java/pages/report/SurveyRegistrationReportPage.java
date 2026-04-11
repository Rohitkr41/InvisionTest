
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
    // ADVANCE SEARCH LOCATORS
    // ======================
    
 // Advance Search Icon Button
    private By advanceSearchIcon = By.xpath("//i[contains(@class,'bi bi-sliders')][1]");
    // agar unique class mile to replace karo


    private By patientNameField = By.xpath("//label[contains(text(),'Patient Name')]/following-sibling::input");
    private By phoneField = By.xpath("//input[@pattern='[0-9]*']");
    private By memberNoField = By.xpath("//label[text()='Member No.']/following-sibling::input");
    private By doorNoField = By.xpath("//label[text()='Door No./Building No.']/following-sibling::input");
    private By areaNameField = By.xpath("//input[@placeholder='Area Name']");
    private By screenedByField = By.xpath("(//input[@pattern=\"^[A-Za-z\\s]*$\"])[2]");

    // Patient Type
    private By patientTypeAll = By.xpath("//input[@value='All']");
    private By patientTypePresent = By.xpath("//input[@value='Present']");
    private By patientTypeAbsent = By.xpath("//input[@value='Absent']");
    private By patientTypeMigrated = By.xpath("//input[@value='Migrated']");
    private By patientTypeRefused = By.xpath("//input[@value='Refused']");

    // Date Filter
    private By surveyDateCheckbox = By.xpath("//input[@type='checkbox']");
    private By fromDateField = By.name("fromDate");
    private By toDateField = By.name("toDate");

    private By advanceSearchBtn = By.xpath("//a[text()='Search']");

    // ======================
    // TOP SEARCH METHODS
    // ======================

 public void selectGroup(String groupName) {

    selectDropdown(groupDropdown, groupName);

    // 🔥 VERY IMPORTANT WAIT (hospital reload)
    wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.elementToBeClickable(hospitalDropdown)
    ));

    System.out.println("✅ Group selected: " + groupName);
}


  public void selectHospital(String hospitalName) {

    selectDropdown(hospitalDropdown, hospitalName);

    System.out.println("✅ Hospital selected: " + hospitalName);
}



    public void clickTopSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        handleNoRecordPopup();
      
    }

    // ======================
    // ADVANCE SEARCH METHODS
    // ======================

  private void type(By locator, String value) {

    if (value == null || value.trim().isEmpty()) return;

    WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    JavascriptExecutor js = (JavascriptExecutor) driver;

    // 🔥 Scroll to element
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", field);

    // 🔥 Wait until clickable
    wait.until(ExpectedConditions.elementToBeClickable(field));

    // 🔥 Force click
    js.executeScript("arguments[0].click();", field);

    // 🔥 Clear field properly
    field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    field.sendKeys(Keys.DELETE);

    // 🔥 Try normal sendKeys first
    try {
        field.sendKeys(value);
    } catch (Exception e) {
        System.out.println("⚠️ sendKeys failed, switching to JS");
    }

    // 🔥 VERIFY if value entered
    String entered = field.getAttribute("value");

    if (entered == null || entered.isEmpty()) {

        // 🔥 FORCE VALUE SET (FINAL FIX)
        js.executeScript("arguments[0].value = arguments[1];", field, value);

        // 🔥 Trigger React/Angular events
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                field
        );
    }

    // 🔥 Blur (validation fix)
    js.executeScript("arguments[0].blur();", field);

    // 🔥 FINAL VERIFY
    String finalValue = field.getAttribute("value");

    if (!value.equals(finalValue)) {
        throw new RuntimeException("❌ Value NOT entered in field: " + value);
    }

    System.out.println("✅ Entered successfully: " + value);
}



   public void enterPatientName(String val) {
	    type(patientNameField, val);
	}

    public void enterPhone(String val) { type(phoneField, val); }
    public void enterMemberNo(String val) { type(memberNoField, val); }
    public void enterDoorNo(String val) { type(doorNoField, val); }
    public void enterAreaName(String val) { type(areaNameField, val); }
    public void enterScreenedBy(String val) { type(screenedByField, val); }

    // Patient Type
    public void selectPatientType(String type) {

        if (type == null) return;

        By locator;

        switch (type.toLowerCase()) {
            case "all": locator = patientTypeAll; break;
            case "present": locator = patientTypePresent; break;
            case "absent": locator = patientTypeAbsent; break;
            case "migrated": locator = patientTypeMigrated; break;
            case "refused": locator = patientTypeRefused; break;
            default: throw new RuntimeException("Invalid type: " + type);
        }

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Date Filter
    public void selectDateRange(String from, String to) {

        if (from == null || to == null) return;

        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(surveyDateCheckbox));

        if (!checkbox.isSelected()) checkbox.click();

        WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(fromDateField));
        fromField.clear();
        fromField.sendKeys(from);

        WebElement toField = wait.until(ExpectedConditions.elementToBeClickable(toDateField));
        toField.clear();
        toField.sendKeys(to);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].blur();", toField);
    }

    public void clickAdvanceSearch() {

    wait.until(ExpectedConditions.elementToBeClickable(advanceSearchBtn)).click();

    // 🔥 SWITCH BACK TO MAIN PAGE
    try {
        driver.switchTo().defaultContent();
        System.out.println("✅ Switched back to main page");
    } catch (Exception e) {
        // ignore
    }
}

    
   public void openAdvanceSearch() {

    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(advanceSearchIcon));

    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

    // 🔥 WAIT for popup
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(),'Survey Registration Search Filter')]")
    ));

    // 🔥 🔥 SWITCH TO IFRAME (IMPORTANT)
    try {
        driver.switchTo().frame(0);   // or use frame locator
        System.out.println("✅ Switched to iframe");
    } catch (Exception e) {
        System.out.println("⚠️ No iframe found (skip)");
    }

    // 🔥 Wait for field inside popup
    wait.until(ExpectedConditions.visibilityOfElementLocated(patientNameField));

    System.out.println("✅ Advance Search popup opened");
}



    // ======================
    // POPUP HANDLER
    // ======================

   public boolean handleNoRecordPopup() {

    try {
        // 🔥 Wait for popup message (flexible text match)
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'No records found for the selected date')]")
        ));

        String text = message.getText().trim();
        System.out.println("⚠️ Popup Message: " + text);

        // 🔥 Validate it's actually "No records"
        if (!text.toLowerCase().contains("no record")) {
            return false; // some other popup
        }

        // 🔥 Wait for OK button
        WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text ()='OK'])[3]")
        ));

        // 🔥 JS click (more reliable than normal click)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);

        // 🔥 Wait for popup to disappear
        wait.until(ExpectedConditions.invisibilityOf(message));

        System.out.println("✅ Popup handled successfully");

        return true;

    } catch (TimeoutException e) {
        // popup nahi aaya
        return false;

    } catch (Exception e) {
        System.out.println("❌ Error handling popup: " + e.getMessage());
        return false;
    }
}
  // ======================
// DROPDOWN HANDLER (FINAL FIX)
// ======================

private void selectDropdown(By locator, String visibleText) {

    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(locator));

    // 🔥 Scroll + click
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
    dropdown.click();

    Select select = new Select(dropdown);

    // 🔥 Wait until options loaded properly
    wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(locator, By.tagName("option")));

    boolean found = false;

    for (WebElement option : select.getOptions()) {

        String text = option.getText().trim();
        System.out.println("OPTION: " + text);

        if (text.equalsIgnoreCase(visibleText.trim())) {

            // 🔥 BEST (instead of selectByVisibleText)
            option.click();
            found = true;
            break;
        }
    }

    if (!found) {
        throw new RuntimeException("❌ Dropdown NOT found: " + visibleText);
    }

    // 🔥 JS EVENTS (VERY IMPORTANT)
    JavascriptExecutor js = (JavascriptExecutor) driver;

    js.executeScript("arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", dropdown);
    js.executeScript("arguments[0].dispatchEvent(new Event('input',{bubbles:true}))", dropdown);
    js.executeScript("arguments[0].blur()", dropdown);

    // 🔥 simulate real user
    driver.findElement(By.tagName("body")).click();
}

   
	   public void performAdvanceSearch(String patient, String phone, String member,
	           String door, String area, String screenedBy,
	           String patientType,
	           String fromDate, String toDate) {
	
	// 🔥 Open Advance Search Popup
	openAdvanceSearch();
	
	// ======================
	// TEXT FIELDS
	// ======================
	
	type(patientNameField, patient);
	type(phoneField, phone);
	type(memberNoField, member);
	type(doorNoField, door);
	type(areaNameField, area);
	type(screenedByField, screenedBy);
	
	// ======================
	// PATIENT TYPE
	// ======================
	
	selectPatientType(patientType);
	
	// ======================
	// DATE FILTER
	// ======================
	
	if (fromDate != null && toDate != null) {
	
	WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(surveyDateCheckbox));
	
	if (!checkbox.isSelected()) {
	checkbox.click();
	}
	
	WebElement from = wait.until(ExpectedConditions.elementToBeClickable(fromDateField));
	from.clear();
	from.sendKeys(fromDate);
	
	WebElement to = wait.until(ExpectedConditions.elementToBeClickable(toDateField));
	to.clear();
	to.sendKeys(toDate);
	
	((JavascriptExecutor) driver).executeScript("arguments[0].blur();", to);
	}
	
	// ======================
	// CLICK SEARCH
	// ======================
	
	WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(advanceSearchBtn));
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
	
	System.out.println("✅ Advance Search executed");
	}
}
