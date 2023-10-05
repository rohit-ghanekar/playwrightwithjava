package com.expleo.playwrightdemoapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

import static com.aventstack.extentreports.Status.INFO;
import static com.expleo.playwrightdemoapp.listeners.ExtentReportListener.test;

public class AdvanceSearchPage {

    private Page page;
    private String advanceSearchButton = "xpath=//a[@aria-label='Advanced Search']";
    private String advanceSearchQueryName = "xpath=//textarea[@name='Name']";
    private String advanceSearchQueryItemID = "xpath=//textarea[@name='ItemID']";
    private String advanceSearchQueryDatasetName = "xpath=//textarea[@name='DatasetName']";

    private String advanceSearchQueryContextName = "xpath=//textarea[@name='AlternateIdContextName']";
    private String advanceSearchNavigation = "xpath=//*[@id='aw_navigation']//div[contains(text(),'Advanced Search')]";

    private String homeLeftMenuButton = "xpath=//div[@class='aw-commandBars aw-commandBar-vertical']//button[@aria-label='Home']";

    private String documentType = "xpath=//div[@aria-label='Type']/preceding-sibling::input";
    private String searchType = "input[name=searchBoxLOVFilterStr]";
    private String selectFolderType = "xpath=//ul[@aria-label='Type']//li//div[@title='Folder']";

    public AdvanceSearchPage(Page page){
        this.page = page;
    }
    public boolean folderSearch(String name){
        page.locator(homeLeftMenuButton).click();
        test.get().log(INFO,"Click on Home Menu option");
        page.waitForSelector(advanceSearchButton).click();
        test.get().log(INFO,"Click on Advance Search option");
        page.getByPlaceholder("Select a search").click();
        test.get().log(INFO,"Click on Search Query Dropdown");
        page.locator("xpath=//div[@title='General...']").click();
        test.get().log(INFO,"Select General option");
        page.locator("xpath=//a[@title='Clear All']").click();
        test.get().log(INFO,"Click on Clear All button");
        page.locator(advanceSearchQueryName).fill(name);
        test.get().log(INFO,"Enter "+name+" in Advance Search query Name field");
        page.locator(documentType).click();
        test.get().log(INFO,"Select Type option");
        page.waitForSelector(searchType).fill("Folder");
        page.waitForSelector(selectFolderType).click();
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        test.get().log(INFO,"Click on Search button");
        return page.waitForSelector("//span[@title='"+name+"']").isVisible();
    }

    public boolean itemSearch(String name){
        page.locator(homeLeftMenuButton).click();
        test.get().log(INFO,"Click on Home Menu option");
        page.waitForSelector(advanceSearchButton).click();
        test.get().log(INFO,"Click on Advance Search option");
        page.getByPlaceholder("Select a search").click();
        test.get().log(INFO,"Click on Search Query Dropdown");
        page.locator("xpath=//div[@title='Item...']").click();
        test.get().log(INFO,"Select Item option");
        page.waitForSelector(advanceSearchQueryContextName).isVisible();
        page.locator("xpath=//a[@title='Clear All']").click();
        test.get().log(INFO,"Click on Clear All button");
        page.locator(advanceSearchQueryName).fill(name);
        test.get().log(INFO,"Enter "+name+" in Advance Search query Name field");
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        test.get().log(INFO,"Click on Search button");
        return page.waitForSelector("span[title="+name+"]").isVisible();
    }

    public boolean itemRevisionSearchByItemID(String itemID){
        page.locator(homeLeftMenuButton).click();
        test.get().log(INFO,"Click on Home Menu option");
        page.waitForSelector(advanceSearchButton).click();
        test.get().log(INFO,"Click on Advance Search option");
        page.getByPlaceholder("Select a search").click();
        test.get().log(INFO,"Click on Search Query Dropdown");
        page.locator("xpath=//div[@title='Item Revision...']").click();
        test.get().log(INFO,"Select Item Revision option");
        page.locator("xpath=//a[@title='Clear All']").click();
        test.get().log(INFO,"Click on Clear All button");
        page.locator(advanceSearchQueryItemID).fill(itemID);
        test.get().log(INFO,"Enter "+itemID+" in Advance Search query Item ID field");
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        test.get().log(INFO,"Click on Search button");
        return page.waitForSelector("//label[@title='"+itemID+"']").isVisible();
    }

    public boolean datasetSearch(String name){
        page.locator(homeLeftMenuButton).click();
        test.get().log(INFO,"Click on Home Menu option");
        page.waitForSelector(advanceSearchButton).click();
        test.get().log(INFO,"Click on Advance Search option");
        page.getByPlaceholder("Select a search").click();
        test.get().log(INFO,"Click on Search Query Dropdown");
        page.locator("xpath=//div[@title='Dataset Name']").click();
        test.get().log(INFO,"Select Dataset Name option");
        page.locator("xpath=//a[@title='Clear All']").click();
        test.get().log(INFO,"Click on Clear All button");
        page.locator(advanceSearchQueryDatasetName).fill(name);
        test.get().log(INFO,"Enter "+name+" in Advance Search query Name field");
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        test.get().log(INFO,"Click on Search button");
        return page.waitForSelector("span[title="+name+"]").isVisible();
    }
}
