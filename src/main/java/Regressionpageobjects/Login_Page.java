package Regressionpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponent;
import RegressionResources.ConfigReader;

public class Login_Page extends AbstractComponent {

    WebDriver driver;
    ConfigReader configReader;

    public Login_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.configReader = new ConfigReader();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#login-user")
    WebElement loginusername;

    @FindBy(id = "login-pass")
    WebElement loginpassword;

    @FindBy(id = "login-submission")
    WebElement loginbutton;

    @FindBy(css = "#login_message")
    WebElement loginerrormessage;

    @FindBy(css = "#logo")
    WebElement logo;

    By logoby = By.cssSelector("#logo");

    public void goTo() {
        String url = configReader.getProperty("url");
        driver.get(url);
    }

    public void LoginApplication() {
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");
        waitForElementToAppear(logoby);
        loginusername.sendKeys(username);
        loginpassword.sendKeys(password);
        loginbutton.click();
    }

    public String geterrormessage() {
        waitForWebElementToAppear(loginerrormessage);
        return loginerrormessage.getText();
    }

    public String LoginSucess() {
        return logo.getText();
    }
}
