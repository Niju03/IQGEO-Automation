package RegressionTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import RegressionTests.TestComponents.BaseTest;
import Regressionpageobjects.HomePage;
import Regressionpageobjects.LoginPage;
import Regressionpageobjects.ProjectCreatePage;

@Listeners(RegressionTests.TestComponents.Listeners.class)
public class CreateProjectTest extends BaseTest {

    @Test(priority = 1)
    public void Loginproject() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        String loginSuccessfully = loginPage.LoginSucess();

        Assert.assertEquals(loginSuccessfully, "IQGeo");

        HomePage homepage = new HomePage(driver);
        homepage.HomeApplication();
        String homepageSuccess = homepage.HomePagesucess();
        Assert.assertEquals(homepageSuccess, "Built by");
    }

    @Test(priority = 2)
    public void Createprojectbutton() throws Exception {
        ProjectCreatePage projectCreatePage = new ProjectCreatePage(driver);

        projectCreatePage.createButton();
        projectCreatePage.searchButton();
        projectCreatePage.inputSearch();
        projectCreatePage.selectSearch();
        projectCreatePage.okButton();
        projectCreatePage.selectContractorOption();
        projectCreatePage.Projectsize();

        String startDate = "2024-06-16";
        String endDate = "2024-06-20";

        projectCreatePage.selectProjectDates(startDate, endDate);
        projectCreatePage.selectSurveyDates(startDate, endDate);
        projectCreatePage.selectPermitDates(startDate, endDate);
        projectCreatePage.selectOltDates(startDate, endDate);

        projectCreatePage.submitButton();
    }

   
 
     
    }

