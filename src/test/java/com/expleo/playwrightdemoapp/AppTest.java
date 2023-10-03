package com.expleo.playwrightdemoapp;

import java.util.Properties;

import com.expleo.playwrightdemoapp.pages.AdvanceSearchPage;
import com.expleo.playwrightdemoapp.pages.FoldersPage;
import com.expleo.playwrightdemoapp.pages.ItemsPage;
import com.expleo.playwrightdemoapp.utils.JsonDataProvider;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.expleo.playwrightdemoapp.Factory.PlaywrightFactory;
import com.expleo.playwrightdemoapp.pages.LoginPage;
import com.microsoft.playwright.Page;
import org.testng.annotations.Parameters;

/**
 * Unit test for simple App.
 */
public class AppTest {

    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    protected LoginPage loginPage;
    protected AdvanceSearchPage advanceSearchPage;
    protected FoldersPage foldersPage;
    protected ItemsPage itemsPage;
    public static JsonDataProvider dp = new JsonDataProvider();
    protected JSONObject jsData;
    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        pf = new PlaywrightFactory();

        prop = pf.init_prop();

        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        page = pf.initBrowser(prop);
        loginPage = new LoginPage(page);
        advanceSearchPage = new AdvanceSearchPage(page);
        foldersPage = new FoldersPage(page);
        itemsPage = new ItemsPage(page);
        jsData = dp.getJsonData();
    }

    @AfterTest
    public void tearDown() {
        page.context().close();
        page.context().browser().close();
    }
}
