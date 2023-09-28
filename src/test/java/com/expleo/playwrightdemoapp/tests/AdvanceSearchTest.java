package com.expleo.playwrightdemoapp.tests;

import com.expleo.playwrightdemoapp.AppTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdvanceSearchTest extends AppTest {

    @BeforeTest
    public void Login() {
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }

    @Test(priority = 1)
    public void AW_FolderSearch(){
        Assert.assertTrue(advanceSearchPage.folderSearch(prop.getProperty("searchString").trim()));
    }

    @Test(priority = 2)
    public void AW_ItemSearch(){
        Assert.assertTrue(advanceSearchPage.itemSearch(prop.getProperty("itemName").trim()));
    }

    @Test(priority = 3)
    public void AW_DatasetSearch(){
        Assert.assertTrue(advanceSearchPage.datasetSearch(prop.getProperty("searchString").trim()));
    }

    @AfterTest
    public void Logout() {
        Assert.assertTrue(loginPage.doLogout());
    }


}
