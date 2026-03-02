
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By username = By.name("loginModel.Username");
    private By password = By.name("loginModel.Password");
    private By captcha = By.cssSelector("input[placeholder='Captcha']");
    private By loginBtn = By.xpath("//button[contains(text(),'Login')]");

    public void login(String user, String pass) {

        type(username, user);
        type(password, pass);

        System.out.println("Enter captcha manually...");

        wait.until(driver ->
                driver.findElement(captcha).getAttribute("value").length() > 0
        );

        click(loginBtn);
        waitForUrlContains("adminDashboard");
    }
}