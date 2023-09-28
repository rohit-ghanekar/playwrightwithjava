package com.expleo.playwrightdemoapp.tests;

import com.expleo.playwrightdemoapp.AppTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FoldersPageTest extends AppTest {

    @BeforeTest
    public void Login() {
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }
    @Test(priority = 3)
    public void AW_FolderCreation() {
        Assert.assertTrue(foldersPage.folderCreation(prop.getProperty("folderName").trim(), prop.getProperty("Description").trim()));
    }

    @Test(priority = 1)
    public void AW_ItemCreation() {
        Assert.assertTrue(advanceSearchPage.folderSearch(prop.getProperty("searchString").trim()));
        Assert.assertTrue(foldersPage.itemCreation(prop.getProperty("itemName").trim(), prop.getProperty("Description").trim()));
    }

    @Test(priority = 2)
    public void AW_ItemPinToHome() {
        Assert.assertTrue(advanceSearchPage.folderSearch(prop.getProperty("searchString").trim()));
        Assert.assertTrue(foldersPage.itemCreation(prop.getProperty("itemName").trim(), prop.getProperty("Description").trim()));
        Assert.assertTrue(foldersPage.pinToHome());
    }

    @Test(priority = 4)
    public void AW_AttachDocument() throws InterruptedException {
        Assert.assertTrue(advanceSearchPage.itemSearch(prop.getProperty("searchString").trim()));
        Assert.assertTrue(foldersPage.attachDocument("AWtestfile.txt"));
    }

    @Test(priority = 5)
    public void AW_AttachDataset() throws InterruptedException {
        Assert.assertTrue(advanceSearchPage.itemSearch(prop.getProperty("searchString").trim()));
        Assert.assertTrue(foldersPage.attachDocument("example.csv"));
    }



    @AfterTest
    public void Logout() {
        Assert.assertTrue(loginPage.doLogout());
    }
}
