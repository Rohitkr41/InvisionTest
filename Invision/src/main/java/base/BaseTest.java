
package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        driver.get(ConfigReader.getProperty("url"));
    }

  
}