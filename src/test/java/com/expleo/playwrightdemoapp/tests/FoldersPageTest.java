package com.expleo.playwrightdemoapp.tests;

import com.expleo.playwrightdemoapp.AppTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FoldersPageTest extends AppTest {

    @BeforeTest
    public void Login() {
        Assert.assertTrue(loginPage.doLogin((String)jsData.get("username"), (String)jsData.get("password")));
    }
    @Test(priority = 3)
    public void AW_FolderCreation() {
        Assert.assertTrue(foldersPage.folderCreation((String)jsData.get("folderName"),(String)jsData.get("Description")));
    }

    @Test(priority = 1)
    public void AW_ItemCreation() {
        Assert.assertTrue(advanceSearchPage.folderSearch((String)jsData.get("searchString")));
        Assert.assertTrue(foldersPage.itemCreation((String)jsData.get("itemName"), (String)jsData.get("Description")));
    }

    @Test(priority = 2)
    public void AW_ItemPinToHome() {
        Assert.assertTrue(advanceSearchPage.folderSearch((String)jsData.get("searchString")));
        Assert.assertTrue(foldersPage.itemCreation((String)jsData.get("itemName"), (String)jsData.get("Description")));
        Assert.assertTrue(foldersPage.pinToHome());
    }

    @Test(priority = 4)
    public void AW_AttachDocument() throws InterruptedException {
        Assert.assertTrue(advanceSearchPage.itemSearch((String)jsData.get("searchString")));
        Assert.assertTrue(foldersPage.attachDocument("AWtestfile.txt"));
    }

    @Test(priority = 5)
    public void AW_AttachDataset() throws InterruptedException {
        Assert.assertTrue(advanceSearchPage.itemSearch((String)jsData.get("searchString")));
        Assert.assertTrue(foldersPage.attachDocument("example.csv"));
    }



    @AfterTest
    public void Logout() {
        Assert.assertTrue(loginPage.doLogout());
    }
}
