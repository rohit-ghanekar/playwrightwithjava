package com.expleo.playwrightdemoapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoldersPage {

    String folderName,itemName,attachedFileName;
    private Page page;
    private String username = "input[name='username']";
    private String password = "input[name='password']";
    private String signInButton = "button[type='submit']";
    private String folderTile = "div[title=Folders]";
    private String createNewPanelButton = "button[aria-label=New]";
    private String addFolderMenuButton = "li[aria-label=Add]";
    private String searchSubmenuTextfield = "input[name=searchBoxLOVFilterStr]";
    
    private String selectSubmenuListItem = "xpath=//ul[@aria-label='typeListProp']//li";
    private String newFolderNameTextfield = "input[name=object_name]";
    private String newFolderDescriptionTextarea = "textarea[name=object_desc]";
    private String submenuAddButton = "xpath=//*[@id='aw_toolsAndInfo']//button[@class='sw-button ']";

    private String openPanelButton = "nav button[aria-label=Open]";
    private String openMenuButton = "div[class=sw-popup-contentContainer] li[aria-label=Open]";
    private String addItemButton = "div[class=sw-popup-contentContainer] li[aria-label=Add]";

    private String newItemName = "div[name=subPanelForm] textarea[name=object_name]";

    private String managePanelButton = "button[aria-label=Manage]";

    private String pinToHomeMenuButton = "xpath=//li[@aria-label='Pin to Home']";
    private String homeLeftMenuButton = "xpath=//div[@class='aw-commandBars aw-commandBar-vertical']//button[@aria-label='Home']";
    private String itemIDLabelInProperties = "span[data-locator=ID]";

    private String documentRelationField = "input[aria-label=Relation]";

    private String attachmentTab = "a[name=attachments]";

    private String addAttachment = "xpath=//button[@aria-label='Add to']";
    private String fileName = "input[name=datasetName]";

    public FoldersPage(Page page){
        this.page = page;
    }
    public boolean folderCreation(String fname,String desc){
        page.locator(homeLeftMenuButton).click();
        page.locator(folderTile).click();
        page.locator(createNewPanelButton).click();
        page.locator(addFolderMenuButton).click();
        page.locator(searchSubmenuTextfield).fill("Folder");
        Locator listEle = page.locator(selectSubmenuListItem);
        listEle.nth(1).click();
        Date date = new java.util.Date();
        String d = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        folderName = fname+d;
        page.waitForSelector(newFolderNameTextfield).fill(folderName);
        page.locator(newFolderDescriptionTextarea).fill(desc);
        page.locator(submenuAddButton).click();
        return page.waitForSelector("xpath=//div[@class='aw-xrt-summaryXrt']//span[contains(text(),'"+folderName+"')]").isVisible();
    }

    public boolean itemCreation(String iName){
        page.locator(openPanelButton).click();
        page.locator(openMenuButton).click();
        page.locator(createNewPanelButton).click();
        page.locator(addItemButton).click();
        page.waitForSelector(searchSubmenuTextfield).fill("Item");
        Locator listEle = page.locator(selectSubmenuListItem);
        listEle.nth(1).click();
        Date date = new java.util.Date();
        String d = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        itemName = iName+d;
        page.waitForSelector(newItemName).fill(itemName);
        page.locator(submenuAddButton).click();
        return page.waitForSelector("xpath=//*[@id='objNavTree']//div[@title='"+itemName+"']").isVisible();
    }

    public boolean pinToHome(){
        page.locator("xpath=//*[@id='objNavTree']//div[@title='"+itemName+"']").first().hover();
        page.locator("xpath=//*[@id='objNavTree']//div[@title='"+itemName+"']//button").click();
        String itemID = page.locator(itemIDLabelInProperties).textContent();
        page.locator(managePanelButton).first().hover();
        page.locator(managePanelButton).click();
        page.waitForSelector(pinToHomeMenuButton).click();
        page.locator(homeLeftMenuButton).click();
        page.locator("xpath=//div[@title='"+itemID+"-"+itemName+"']").scrollIntoViewIfNeeded();
        return page.waitForSelector("xpath=//div[@title='"+itemID+"-"+itemName+"']").isVisible();
    }

    public boolean attachDocument(String fName) {
        page.locator(attachmentTab).click();
        page.locator(addAttachment).click();
        File file = new File("src/test/resources/"+fName);
        String absolutePath = file.getAbsolutePath();
        page.setInputFiles("//input[@type='file']",
                Paths.get(absolutePath));
        Date date = new java.util.Date();
        String d = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        attachedFileName = "AWtestfile"+d;
        page.waitForSelector(documentRelationField);
        page.waitForSelector(fileName).fill(attachedFileName);
        page.waitForSelector(submenuAddButton).click();
        return page.waitForSelector("xpath=//div[@title='"+attachedFileName+"']").isVisible();
    }
}
