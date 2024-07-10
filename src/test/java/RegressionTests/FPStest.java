package RegressionTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import RegressionTests.TestComponents.BaseTest;
import Regressionpageobjects.Fps_Page;
import Regressionpageobjects.Home_Page;
import Regressionpageobjects.Login_Page;



@Listeners(RegressionTests.TestComponents.Listeners.class)
public class FPStest extends BaseTest {	

	@Test(priority=3)
	public void FPS_Project_Link() throws InterruptedException, CsvValidationException, IOException {
	
	
		Login_Page login_Page = new Login_Page(driver);
		String loginSuccessfully = login_Page.LoginSucess();

		Assert.assertEquals(loginSuccessfully, "IQGeo");

		Home_Page homepage = new Home_Page(driver);
		homepage.FPSApplication();
		
		Fps_Page fps_Page = new Fps_Page(driver);
		fps_Page.FPSsearch();
		fps_Page.Editscenario();
		fps_Page.CMtrackingno();
		fps_Page.Save();
	
		
		
}
	
	
	
}
