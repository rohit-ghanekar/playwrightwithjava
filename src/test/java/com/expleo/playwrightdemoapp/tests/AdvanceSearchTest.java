package com.expleo.playwrightdemoapp.tests;

import com.expleo.playwrightdemoapp.AppTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdvanceSearchTest extends AppTest {

    @BeforeTest
    public void Login() {
        Assert.assertTrue(loginPage.doLogin((String)jsData.get("username"), (String)jsData.get("password")));
    }

    @Test
    public void AW_FolderSearch(){
        Assert.assertTrue(advanceSearchPage.folderSearch((String)jsData.get("searchString")));
    }

    @Test
    public void AW_ItemSearch(){
        Assert.assertTrue(advanceSearchPage.itemSearch((String)jsData.get("itemName")));
    }

    @Test
    public void AW_DatasetSearch(){
        Assert.assertTrue(advanceSearchPage.datasetSearch((String)jsData.get("searchString")));
    }

    @AfterTest
    public void Logout() {
        Assert.assertTrue(loginPage.doLogout());
    }


}
