//package pages.report;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//import pages.BasePage;
//
//public class SurveyRegistrationReportPage extends BasePage {
//
//    public SurveyRegistrationReportPage(WebDriver driver) {
//        super(driver);
//    }
//
//    // =============================
//    // PAGE HEADER (OPTIONAL WAIT)
//    // =============================
//
//    By pageHeader = By.xpath("//h4[contains(text(),'Survey Registration Report')]");
//
//    // =============================
//    // TOP FILTERS
//    // =============================
//
//    By groupDropdown = By.xpath("(//select[contains(@class,'search-input')])[1]");
//    By hospitalDropdown = By.xpath("(//select[contains(@class,'search-input')])[2]");
//    By topSearchBtn = By.xpath("//*[@id='top-headings']/div[2]/div/form/a[1]/img");
//
//    // =============================
//    // ADVANCE SEARCH
//    // =============================
//
//    By advanceSearchBtn = By.xpath("//a[@title='Advance Search']");
//
//    By patientName = By.xpath("(//input[@pattern='^[A-Za-z\\s]*$'])[1]");
//    By phoneNo = By.xpath("//input[@placeholder='Phone No.']");
//    By memberNo = By.xpath("//input[@placeholder='Member No.']");
//
//    By fromDate = By.name("fromDate");
//    By toDate = By.name("toDate");
//
//    By advanceSearchSubmit = By.xpath("//a[normalize-space()='Search']");
//
//    // =============================
//    // RESULT TABLE
//    // =============================
//
//    By resultRow = By.xpath("//table//tbody//tr");
//
//    // =============================
//    // COMMON METHODS
//    // =============================
//
//   protected void selectDropdown(By locator, String visibleText) {
//
//    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//
//    Select select = new Select(dropdown);
//
//    // 🔥 wait till options loaded
//    wait.until(driver -> select.getOptions().size() > 1);
//
//    boolean found = false;
//
//    for (WebElement option : select.getOptions()) {
//
//        String text = option.getText().trim();
//        String value = option.getAttribute("value");
//
//        System.out.println("TEXT: " + text + " | VALUE: " + value);
//
//        if (text.equalsIgnoreCase(visibleText.trim())) {
//
//            // 🔥 select by VALUE (more reliable)
//            select.selectByValue(value);
//
//            // 🔥 JS EVENTS (VERY IMPORTANT)
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", dropdown);
//            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", dropdown);
//            js.executeScript("arguments[0].blur();", dropdown);
//
//            found = true;
//            break;
//        }
//    }
//
//    if (!found) {
//        throw new RuntimeException("❌ Dropdown value not found: " + visibleText);
//    }
//}
//
//
//    public void safeClick(By locator) {
//
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
//
//        try {
//            element.click();
//        } catch (Exception e) {
//            ((JavascriptExecutor) driver)
//                    .executeScript("arguments[0].click();", element);
//        }
//    }
//
//    // =============================
//    // TOP SEARCH METHODS
//    // =============================
//
//    public void selectGroup(String group) {
//        selectDropdown(groupDropdown, group);
//    }
//
//    public void selectHospital(String hospital) {
//        selectDropdown(hospitalDropdown, hospital);
//    }
//
//
//  public void clickSearch() {
//
//    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(topSearchBtn));
//
//    ((JavascriptExecutor) driver)
//            .executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
//
//    try {
//        btn.click();
//    } catch (Exception e) {
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].click();", btn);
//    }
//
//    // 🔥 ALERT HANDLE FIRST
//    if (handleNoRecordPopup()) {
//        return;
//    }
//
//    // 🔥 otherwise wait for table
//    wait.until(ExpectedConditions.presenceOfElementLocated(resultRow));
//
//    System.out.println("✅ Top Search done");
//}
//
//
//
//
//
//    public void topSearch(String group, String hospital) {
//
//        selectGroup(group);
//        selectHospital(hospital);
//        clickSearch();
//    }
//
//    
//    // =============================
//    // ADVANCE SEARCH
//    // =============================
//
//    public boolean isAdvanceSearchOpen() {
//        try {
//            return driver.findElement(patientName).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public void openAdvanceSearch() {
//
//        if (!isAdvanceSearchOpen()) {
//            safeClick(advanceSearchBtn);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(patientName));
//        }
//    }
//
//    public void enterPatientName(String name) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(patientName));
//        el.clear();
//        el.sendKeys(name);
//    }
//
//    public void enterPhone(String phone) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNo));
//        el.clear();
//        el.sendKeys(phone);
//    }
//
//    public void enterMemberNo(String member) {
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(memberNo));
//        el.clear();
//        el.sendKeys(member);
//    }
//
//    public void setDate(String from, String to) {
//
//        if (from != null) {
//            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));
//            el.clear();
//            el.sendKeys(from);
//        }
//
//        if (to != null) {
//            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(toDate));
//            el.clear();
//            el.sendKeys(to);
//        }
//    }
//
//    public void advanceSearch(String group, String hospital,
//                              String name, String phone,
//                              String member, String from, String to) {
//
//        topSearch(group, hospital);
//
//        openAdvanceSearch();
//
//        if (name != null) enterPatientName(name);
//        if (phone != null) enterPhone(phone);
//        if (member != null) enterMemberNo(member);
//
//        setDate(from, to);
//
//        safeClick(advanceSearchSubmit);
//
//        if (handleNoRecordPopup()) return;
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(resultRow));
//    }
//
//    // =============================
//    // CLEAN REUSABLE METHODS
//    // =============================
//
//    public void searchByPatientAndDate(String group, String hospital,
//                                       String name, String from, String to) {
//
//        advanceSearch(group, hospital, name, null, null, from, to);
//    }
//
//    public void searchByPhoneAndDate(String group, String hospital,
//                                     String phone, String from, String to) {
//
//        advanceSearch(group, hospital, null, phone, null, from, to);
//    }
//
//    public void searchByMemberAndDate(String group, String hospital,
//                                      String member, String from, String to) {
//
//        advanceSearch(group, hospital, null, null, member, from, to);
//    }
//
//    public void searchByDate(String group, String hospital,
//                             String from, String to) {
//
//        advanceSearch(group, hospital, null, null, null, from, to);
//    }
//
//
//    // =============================
//    // RESULT VALIDATION
//    // =============================
//
//    public boolean isResultDisplayed() {
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(resultRow));
//
//        return driver.findElements(resultRow).size() > 0;
//    }
//
//    // =============================
//    // POPUP HANDLING
//    // =============================
//    
//    public boolean handleNoRecordPopup() {
//
//        try {
//            WebElement alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//div[contains(@class,'alert-body')]//p[contains(text(),'No records')]")
//            ));
//
//            System.out.println("⚠️ Alert: " + alertMsg.getText());
//
//            WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("(//div[contains(@class,'alert-btn')]//button[text()='OK'])[3]")
//            ));
//
//            okBtn.click();
//
//            // 🔥 wait for alert to disappear
//            wait.until(ExpectedConditions.invisibilityOf(alertMsg));
//
//            System.out.println("✅ Alert closed automatically");
//
//            return true;
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//}


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
