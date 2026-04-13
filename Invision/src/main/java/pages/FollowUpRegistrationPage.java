
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FollowUpRegistrationPage extends BasePage {

    public FollowUpRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // ================================
    // 🔹 LOCATORS
    // ================================

    By memberNumber = By.name("searchRegisterDomain.MedicalNo");
    By searchButton = By.xpath("(//*[@id='main']//form//a)[2]");
    // Fetch
	 By fetchdata = By.xpath("//table//tbody//tr[1]//td[7]//i");
    By yesbtn = By.xpath("//button[.='Yes']");

    By discountCheckbox = By.xpath("//*[@id='main']//div[3]//div[2]//div[4]//input");
    By discountTextField = By.xpath("(//*[@id='main']//form//div[5]//input)[5]");
    By discountRemarkDropdown = By.xpath("//label[contains(text(),'Discount Remark')]/following::select[1]");
    By modeDropdown = By.xpath("//label[contains(text(),'Mode')]/following::select[1]");
    By transactionId = By.xpath("(//*[@id='main']//div[8]//input)[2]");
    By registerPatient = By.id("RM_btnSubmit");

    // ================================
    // 🔹 BASIC ACTIONS
    // ================================

    public void enterMemberNumber(String number) {
        type(memberNumber, number);
    }

    public void clickSearch() {
        click(searchButton);
    }

    public void clickFetch() {
        click(fetchdata);
    }

    public void clickYes() {
        click(yesbtn);
    }

    public void clickRegisterPatient() {
    WebElement btn = driver.findElement(registerPatient);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", btn);
}

    public void enterDiscountAmount(String amount) {
        type(discountTextField, amount);
    }

    public void enterTransactionId(String txnId) {
        type(transactionId, txnId);
    }

    public void clickDiscountCheckbox() {
        click(discountCheckbox);
    }

    // ================================
    // 🔹 DROPDOWNS
    // ================================

    public void selectDiscountRemark(String remark) {
        waitForVisibility(discountRemarkDropdown);
        Select dropdown = new Select(driver.findElement(discountRemarkDropdown));

        for (WebElement option : dropdown.getOptions()) {
            if (option.getText().trim().equalsIgnoreCase(remark)) {
                option.click();
                return;
            }
        }
    }

    public void selectMode(String modeType) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(modeDropdown));

        Select mode = new Select(dropdown);
        mode.selectByVisibleText(modeType);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                dropdown
        );

        dropdown.sendKeys(Keys.TAB);
    }

    // ================================
    // 🔹 CONDITIONS
    // ================================

    public boolean isPaymentSectionEnabled() {
        try {
            WebElement mode = driver.findElement(modeDropdown);
            return mode.isDisplayed() && mode.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDiscountCheckboxClickable() {
    try {
        wait.until(ExpectedConditions.elementToBeClickable(discountCheckbox));
        return true;
    } catch (Exception e) {
        return false;
    }
}

    // ================================
    // 🚀 FINAL METHOD
    // ================================

   public void handlePaymentAndRegister(String modeType, String remark, String txnId, String discountAmt) {

    if (isPaymentSectionEnabled()) {

        System.out.println("✅ Payment section enabled → trying payment");

        try {
            // 🔥 TRY clicking checkbox (no pre-check)
            clickDiscountCheckbox();

            // Agar click successful ho gaya tabhi aage badho
            enterDiscountAmount(discountAmt);
            selectDiscountRemark(remark);
            selectMode(modeType);
            enterTransactionId(txnId);

        } catch (Exception e) {

            System.out.println("⚠️ Discount checkbox not clickable → skipping payment بالكامل");
        }

    } else {

        System.out.println("⚠️ Payment section disabled → skipping payment");
    }

    // ✅ ALWAYS REGISTER
    clickRegisterPatient();
}
}