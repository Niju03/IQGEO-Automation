package RegressionTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import RegressionTests.TestComponents.BaseTest;
import Regressionpageobjects.Home_Page;
import Regressionpageobjects.Login_Page;
import Regressionpageobjects.Project_Creation_Page;

@Listeners(RegressionTests.TestComponents.Listeners.class)
public class CreateProjectTest extends BaseTest {

@Test(priority=1)
	public void Application_Login() throws Exception {
		Login_Page login_Page = new Login_Page(driver);
		String loginSuccessfully = login_Page.LoginSucess();

		Assert.assertEquals(loginSuccessfully, "IQGeo");

		Home_Page homepage = new Home_Page(driver);
		homepage.HomeApplication();
		String homepageSuccess = homepage.HomePagesucess();
		Assert.assertEquals(homepageSuccess, "Built by");
	}

@Test(priority=2)
	public void Project_Creation() throws Exception {
		Project_Creation_Page project_Creation_Page = new Project_Creation_Page(driver);

		project_Creation_Page.createButton();
		project_Creation_Page.searchButton();
		project_Creation_Page.inputSearch();
		project_Creation_Page.selectSearch();
		project_Creation_Page.okButton();
		project_Creation_Page.selectContractorOption();
		project_Creation_Page.Projectsize();

		String startDate = "2024-07-28";
		String endDate = "2024-07-29";

		project_Creation_Page.selectProjectDates(startDate, endDate);
		project_Creation_Page.selectSurveyDates(startDate, endDate);
		project_Creation_Page.selectPermitDates(startDate, endDate);
		project_Creation_Page.selectOltDates(startDate, endDate);

		project_Creation_Page.submitButton();

		project_Creation_Page.wirecenterid();

		
	}

}
