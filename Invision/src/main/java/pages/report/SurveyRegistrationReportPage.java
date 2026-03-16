package pages.report;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



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

    // Select Group Dropdown
    private By groupDropdown = By.xpath("(//select[contains(@class,'search-input')])[1]");

    // Select Hospital Dropdown
    private By hospitalDropdown = By.xpath("(//select[contains(@class,'search-input')])[2]");

    // Search Button
    private By searchButton = By.xpath("//*[@id=\"top-headings\"]/div[2]/div/form/a[1]/img");

    // Table Rows
    private By tableRows = By.xpath("//table//tbody//tr");


    // ======================
    // SELECT GROUP
    // ======================

    public void selectGroup(String groupName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(groupDropdown));

        Select group = new Select(driver.findElement(groupDropdown));
        group.selectByVisibleText(groupName);
    }

    // ======================
    // SELECT HOSPITAL
    // ======================

    public void selectHospital(String hospitalName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(hospitalDropdown));

        Select hospital = new Select(driver.findElement(hospitalDropdown));
        hospital.selectByVisibleText(hospitalName);
    }

    // ======================
    // CLICK SEARCH
    // ======================

    public void clickSearch() {

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // ======================
    // GET RESULT COUNT
    // ======================

    public int getSurveyResultCount() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        return driver.findElements(tableRows).size();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'invision' of https://github.com/Rohitkr41/InvisionTest
