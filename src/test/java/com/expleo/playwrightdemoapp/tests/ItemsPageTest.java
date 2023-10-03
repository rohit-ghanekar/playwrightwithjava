package com.expleo.playwrightdemoapp.tests;

import com.expleo.playwrightdemoapp.AppTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ItemsPageTest extends AppTest {

    @BeforeTest
    public void Login() {
        Assert.assertTrue(loginPage.doLogin((String)jsData.get("username"), (String)jsData.get("password")));
    }

    @Test(priority = 2)
    public void AW_ItemRevise() {
        Assert.assertTrue(advanceSearchPage.itemRevisionSearchByItemID((String)jsData.get("searchItemID")));
        Assert.assertTrue(itemsPage.itemRevise());
    }

     @AfterTest
    public void Logout() {
        Assert.assertTrue(loginPage.doLogout());
    }
}
