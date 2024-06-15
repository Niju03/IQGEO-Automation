package RegressionTests.TestComponents;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Regressionpageobjects.LoginPage;

public class Errorvalidations extends BaseTest {

    @Test
    public void createproject() throws IOException {
        // Initialize LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Perform login action
    //    loginPage.LoginApplication("username", "password");

        // Assert error message
        Assert.assertEquals(loginPage.geterrormessage(), "Invalid credentials or not an authorised user");
    }
}
