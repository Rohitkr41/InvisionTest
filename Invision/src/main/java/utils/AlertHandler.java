package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertHandler {

    public static void closeNoDataAlert(WebDriver driver) {

        try {

            By alertMessage = By.xpath("//div[contains(text(),'No data found')]");
            By okButton = By.xpath("//button[contains(text(),'OK')]");

            if(driver.findElements(alertMessage).size() > 0) {

                WaitUtils.waitForElementClickable(driver, okButton).click();

                System.out.println("No Data Alert Closed");

            }

        }
        catch(Exception e) {

            System.out.println("No Alert Present");

        }

    }

}
