package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class VisualAcuityRefractionPage extends BasePage {

    public VisualAcuityRefractionPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================

    By visualAcuityMenu = By.xpath("//*[@id='side-box-nav']/li[4]/a");

    // =============================
    // VISUAL ACUITY SECTION
    // =============================

    By wearingGlassesYes = By.id("RM_rdbYes");
    By periodField = By.id("numberInput");
    By durationDropdown = By.xpath("//*[@id='box-main']//div[2]/select");

    By ableCheckVisionYes = By.id("RM_rdbyess");
    By ableCheckVisionNo = By.id("RM_rdbnoss");

    By remarksField = By.xpath("(//*[@id='box-main']//div/input)[2]");
    By visionWithGlassesNo = By.xpath("(//*[@id='box-main']//div[2]/label/input)[3]");

    // =============================
    // PRESENTING VISION
    // =============================

    By reDVA = By.xpath("(//*[@id='box-main']//div[5]//input)[1]");
    By reNVA = By.xpath("(//*[@id='box-main']//div[5]//input)[2]");
    By rePinhole = By.xpath("(//*[@id='box-main']//div[5]//input)[3]");

    By leDVA = By.xpath("(//*[@id='box-main']//div[6]//div/input)[1]");
    By leNVA = By.xpath("(//*[@id='box-main']//div[6]//div/input)[2]");
    By lePinhole = By.xpath("(//*[@id='box-main']//div[6]//div/input)[3]");

    By saveVisualAcuity = By.xpath("//button[contains(text(),'Save Visual Acuity')]");

    // =============================
    // RIGHT EYE (RE)
    // =============================

    By rePrevSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[1]");
    By rePrevCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[1]");
    By rePrevAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[1]");
    By rePrevADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[1]");

    By reDrySPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[2]");
    By reDryCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[2]");
    By reDryAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[2]");

    By reWetSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[3]");
    By reWetCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[3]");
    By reWetAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[3]");

    By reAccSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[4]");
    By reAccCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[4]");
    By reAccAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[4]");

    By reBCVA = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[2]");
    By reADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[5]");
    By reNVA1 = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[5]");
    By reNPC = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[5]");
    By reRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[6]");

    By reIOPTime = By.xpath("(//input[@type='time'])[1]");
    By reIOPValue = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[6]");
    By reIOPRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[8]//input)[1]");

    // =============================
    // LEFT EYE (LE)
    // =============================

    By lePrevSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[1]");
    By lePrevCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[7]");
    By lePrevAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[6]");
    By lePrevADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[3]");

    By leDrySPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[2]");
    By leDryCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[8]");
    By leDryAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[7]");

    By leWetSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[3]");
    By leWetCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[9]");
    By leWetAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[8]");

    By leAccSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[4]");
    By leAccCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[10]");
    By leAccAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[9]");

    By leBCVA = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[4]");
    By leADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[5]");
    By leNVA1 = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[11]");
    By leNPC = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[10]");
    By leRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[6]");

    By leIOPTime = By.xpath("(//input[@type='time'])[2]");
    By leIOPValue = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[12]");
    By leIOPRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[8]//input)[2]");

    By saveRefraction = By.xpath("//button[contains(text(),'Save Refraction')]");

    // =============================
    // CLICK VISUAL ACUITY MENU
    // =============================

    public void clickVisualAcuityMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(visualAcuityMenu)).click();
    }

    // =============================
    // ADD VISUAL ACUITY
    // =============================

    public void addVisualAcuity() {

        wait.until(ExpectedConditions.elementToBeClickable(wearingGlassesYes)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodField)).sendKeys("2");

        driver.findElement(durationDropdown).sendKeys("Week");
        wait.until(ExpectedConditions.elementToBeClickable(ableCheckVisionYes)).click();

        driver.findElement(remarksField).sendKeys("Vision normal");
        wait.until(ExpectedConditions.elementToBeClickable(visionWithGlassesNo)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(reDVA)).sendKeys("6/6");
        driver.findElement(reNVA).sendKeys("N6");
        driver.findElement(rePinhole).sendKeys("6/6");

        driver.findElement(leDVA).sendKeys("6/6");
        driver.findElement(leNVA).sendKeys("N6");
        driver.findElement(lePinhole).sendKeys("6/6");

        driver.findElement(saveVisualAcuity).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(rePrevSPH));
    }

    // =============================
    // ADD REFRACTION
    // =============================

    public void addRefraction() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(rePrevSPH)).sendKeys("-1.00");
        driver.findElement(rePrevCYL).sendKeys("-0.50");
        driver.findElement(rePrevAXIS).sendKeys("180");
        driver.findElement(rePrevADD).sendKeys("1.00");

        driver.findElement(reDrySPH).sendKeys("-1.25");
        driver.findElement(reDryCYL).sendKeys("-0.50");
        driver.findElement(reDryAXIS).sendKeys("170");

        driver.findElement(reWetSPH).sendKeys("-1.00");
        driver.findElement(reWetCYL).sendKeys("-0.25");
        driver.findElement(reWetAXIS).sendKeys("175");

        driver.findElement(reAccSPH).sendKeys("-1.00");
        driver.findElement(reAccCYL).sendKeys("-0.50");
        driver.findElement(reAccAXIS).sendKeys("180");
        driver.findElement(reBCVA).sendKeys("6/6");

        driver.findElement(reADD).sendKeys("1.25");
        driver.findElement(reNVA1).sendKeys("N6");
        driver.findElement(reNPC).sendKeys("10");

        driver.findElement(reRemarks).sendKeys("Right eye normal");
        driver.findElement(reIOPValue).sendKeys("16");
        driver.findElement(reIOPRemarks).sendKeys("IOP normal");

        driver.findElement(lePrevSPH).sendKeys("-1.25");
        driver.findElement(lePrevCYL).sendKeys("-0.75");
        driver.findElement(lePrevAXIS).sendKeys("170");
        driver.findElement(lePrevADD).sendKeys("1.00");

        driver.findElement(leDrySPH).sendKeys("-1.50");
        driver.findElement(leDryCYL).sendKeys("-0.50");
        driver.findElement(leDryAXIS).sendKeys("165");

        driver.findElement(leWetSPH).sendKeys("-1.25");
        driver.findElement(leWetCYL).sendKeys("-0.25");
        driver.findElement(leWetAXIS).sendKeys("170");

        driver.findElement(leAccSPH).sendKeys("-1.25");
        driver.findElement(leAccCYL).sendKeys("-0.75");
        driver.findElement(leAccAXIS).sendKeys("170");
        driver.findElement(leBCVA).sendKeys("6/6");

        driver.findElement(leADD).sendKeys("1.25");
        driver.findElement(leNVA1).sendKeys("N6");
        driver.findElement(leNPC).sendKeys("10");

        driver.findElement(leRemarks).sendKeys("Left eye normal");
        driver.findElement(leIOPValue).sendKeys("15");
        driver.findElement(leIOPRemarks).sendKeys("IOP normal");

        driver.findElement(saveRefraction).click();
    }
}