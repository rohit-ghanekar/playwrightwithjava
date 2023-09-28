package com.expleo.playwrightdemoapp.tests;

import com.expleo.playwrightdemoapp.AppTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class LoginPageTest extends AppTest {

    @Test(priority = 1)
    public void AW_LaunchAndLogin() {
        Assert.assertTrue(loginPage.doLoginAndLogout(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }
}
