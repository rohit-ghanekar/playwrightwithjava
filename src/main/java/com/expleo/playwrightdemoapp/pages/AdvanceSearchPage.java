package com.expleo.playwrightdemoapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

public class AdvanceSearchPage {

    private Page page;
    private String advanceSearchButton = "xpath=//a[@aria-label='Advanced Search']";
    private String advanceSearchQueryName = "xpath=//textarea[@name='Name']";
    private String advanceSearchQueryItemID = "xpath=//textarea[@name='ItemID']";
    private String advanceSearchQueryDatasetName = "xpath=//textarea[@name='DatasetName']";
    private String advanceSearchNavigation = "xpath=//*[@id='aw_navigation']//div[contains(text(),'Advanced Search')]";

    private String homeLeftMenuButton = "xpath=//div[@class='aw-commandBars aw-commandBar-vertical']//button[@aria-label='Home']";

    public AdvanceSearchPage(Page page){
        this.page = page;
    }
    public boolean folderSearch(String name){
        page.locator(homeLeftMenuButton).click();
        page.waitForSelector(advanceSearchButton).click();
        page.getByPlaceholder("Select a search").click();
        page.locator("xpath=//div[@title='General...']").click();
        page.locator("xpath=//a[@title='Clear All']").click();
        page.locator(advanceSearchQueryName).fill(name);
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        return page.waitForSelector("span[title="+name+"]").isVisible();
    }

    public boolean itemSearch(String name){
        page.locator(homeLeftMenuButton).click();
        page.waitForSelector(advanceSearchButton).click();
        page.getByPlaceholder("Select a search").click();
        page.locator("xpath=//div[@title='Item...']").click();
        page.locator("xpath=//a[@title='Clear All']").click();
        page.locator(advanceSearchQueryName).fill(name);
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        return page.waitForSelector("span[title="+name+"]").isVisible();
    }

    public boolean itemRevisionSearchByItemID(String itemID){
        page.locator(homeLeftMenuButton).click();
        page.waitForSelector(advanceSearchButton).click();
        page.getByPlaceholder("Select a search").click();
        page.locator("xpath=//div[@title='Item Revision...']").click();
        page.locator("xpath=//a[@title='Clear All']").click();
        page.locator(advanceSearchQueryItemID).fill(itemID);
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        return page.waitForSelector("//label[@title='"+itemID+"']").isVisible();
    }

    public boolean datasetSearch(String name){
        page.locator(homeLeftMenuButton).click();
        page.waitForSelector(advanceSearchButton).click();
        page.getByPlaceholder("Select a search").click();
        page.locator("xpath=//div[@title='Dataset Name']").click();
        page.locator("xpath=//a[@title='Clear All']").click();
        page.locator(advanceSearchQueryDatasetName).fill(name);
        page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
        return page.waitForSelector("span[title="+name+"]").isVisible();
    }
}
