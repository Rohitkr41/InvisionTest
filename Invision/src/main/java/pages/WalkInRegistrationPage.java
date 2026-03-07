package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WalkInRegistrationPage extends BasePage {

    public WalkInRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Basic Details

    By firstName = By.xpath("//*[@id='R_txtFirstName']/input");
    By lastName = By.xpath("//*[@id='main']//div[4]//input");

    By genderMale = By.xpath("//*[@id='R_rdoGender']/div/div[1]/label");

    By dob = By.xpath("//*[@id='main']//form//div[6]//input");

    By nextOfKin = By.xpath("//*[@id='main']//form//div[8]//input");
    By phoneNumber = By.id("phoneNumberInput");

    // Occupation Dropdown
    By occupationDropdown = By.id("RM_ddlCategory");

    // Education Dropdown
    By educationDropdown = By.xpath("//label[contains(text(),'Education')]/following::select[1]");

    // Identity Type
    By identityType = By.xpath("//label[contains(text(),'Identity Type')]/following::select[1]");
    By identityNumber = By.xpath("//*[@id='main']//form//div[13]//input");

    // Address
    By houseNo = By.xpath("//*[@id='main']//div[2]//div[2]//input");

    // Village Field
    By villageField = By.name("selectedRegistrationDomain.AreaName");

    // Discount Checkbox
    By discountCheckbox = By.xpath("//*[@id='main']//div[3]//div[2]//div[4]//input");

    // Discount Amount TextField
    By discountTextField = By.xpath("(//*[@id='main']//form//div[5]//input)[5]");

    // Discount Remark Dropdown
    By discountRemarkDropdown = By.xpath("//label[contains(text(),'Discount Remark')]/following::select[1]");

    // Mode Dropdown
    By modeDropdown = By.xpath("//label[contains(text(),'Mode')]/following::select[1]");

    // Transaction Id
    By transactionId = By.xpath("(//*[@id='main']//div[8]//input)[2]");
    
    //registrationBtn
    By registrationBtn = By.id("RM_btnSubmit");


    // Occupation Select
    public void selectOccupation() {

        wait.until(ExpectedConditions.elementToBeClickable(occupationDropdown));

        Select occupation = new Select(driver.findElement(occupationDropdown));
        occupation.selectByVisibleText("Private service");
    }


    // Identity Type Select
    public void selectIdentityType() {

        wait.until(ExpectedConditions.elementToBeClickable(identityType));

        Select idType = new Select(driver.findElement(identityType));
        idType.selectByVisibleText("Nationalid");
    }


    // Village Auto Select
    public void selectVillage(String villageName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(villageField));

        driver.findElement(villageField).sendKeys(villageName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(villageField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(villageField).sendKeys(Keys.ENTER);
    }


    // Apply Discount
    public void applyDiscount(String amount) {

        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(discountCheckbox));
        checkbox.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement discountField = wait.until(
                ExpectedConditions.elementToBeClickable(discountTextField));

        discountField.clear();
        discountField.sendKeys(amount);
    }


    // Select Discount Remark
    public void selectDiscountRemark(String remark) {

        wait.until(ExpectedConditions.elementToBeClickable(discountRemarkDropdown));

        Select remarkDropdown = new Select(driver.findElement(discountRemarkDropdown));
        remarkDropdown.selectByVisibleText(remark);
    }


    // Mode Select
    public void selectMode(String modeType) {

        wait.until(ExpectedConditions.elementToBeClickable(modeDropdown));

        Select mode = new Select(driver.findElement(modeDropdown));
        mode.selectByVisibleText(modeType);
    }



    // Walk-In Registration
    public void registerWalkInPatient() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

        type(firstName,"Rahul");
        type(lastName,"Singh");

        click(genderMale);

        type(dob,"10-05-1995");

        type(nextOfKin,"Ramesh");
        type(phoneNumber,"9876543210");

        // Occupation
        selectOccupation();

        // Education
        driver.findElement(educationDropdown).sendKeys("Graduate");

        // Identity
        selectIdentityType();
        type(identityNumber,"123456789012");

        // Address
        type(houseNo,"123 Main Street");

        // Village
        selectVillage("Rampur");

        // Apply Discount
        applyDiscount("10");

        // Discount Remark
        selectDiscountRemark("Free visit");

        // Mode
        selectMode("UPI");

        // Transaction Id
        driver.findElement(transactionId).sendKeys("gpayr373677343");
       
        //registrationBtn
        click(registrationBtn);
    }
}
