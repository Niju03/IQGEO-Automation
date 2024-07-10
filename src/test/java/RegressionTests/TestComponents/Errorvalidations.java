package RegressionTests.TestComponents;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Regressionpageobjects.Login_Page;

public class Errorvalidations extends BaseTest {

    @Test
    public void createproject() throws IOException {
        // Initialize LoginPage object
        Login_Page login_Page = new Login_Page(driver);

        // Perform login action
    //    loginPage.LoginApplication("username", "password");

        // Assert error message
        Assert.assertEquals(login_Page.geterrormessage(), "Invalid credentials or not an authorised user");
    }
}
