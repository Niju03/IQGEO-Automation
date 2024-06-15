package Regressionpageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{
	
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}

	
	@FindBy(xpath="//a[@href='mywcm_manager.html']")
	WebElement  ConstructionAdmin;

	By managerby = By.xpath("//a[@href='mywcm_manager.html']");
	
	

	@FindBy(css="#built_by_footer")
	WebElement  homepage;
	By homepageby = By.cssSelector("#built_by_footer");
	
	public void HomeApplication() 
	{
		waitForElementToAppear(managerby);
		ConstructionAdmin.click();
	
	}
	
	
	public String HomePagesucess() {
    
		
		waitForElementToAppear(homepageby);
	
		return homepage.getText();
    
	
	}
	
	
}

