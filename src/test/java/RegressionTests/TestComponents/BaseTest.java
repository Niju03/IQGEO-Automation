package RegressionTests.TestComponents;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeClass;
import Regressionpageobjects.LoginPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    public static final String SCREENSHOT_DIR = "C:\\Users\\niranjans\\eclipse-workspace\\IQGEO_Automation\\Screenshots\\";

    @BeforeClass
    public void Launchapplication() {
        // Initialize the WebDriver (ChromeDriver in this case)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\niranjans\\eclipse-workspace\\IQGEO_Automation\\my driver\\chromedriver.exe");
        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();

        // Initialize the page objects
        loginPage = new LoginPage(driver);

        // Open the application URL
        loginPage.goTo();

        // Log in to the application using the credentials from the properties file
        loginPage.LoginApplication();
    }

    
    public String captureScreenshot(String methodName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = SCREENSHOT_DIR + methodName + "_" + timeStamp + ".png";
        try {
            FileHandler.copy(screenshot, new File(filePath));
            System.out.println("Screenshot captured: " + filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
