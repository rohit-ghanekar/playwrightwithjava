package com.expleo.playwrightdemoapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemsPage {

    private Page page;
    private String createNewPanelButton = "button[aria-label=New]";
    private String submenuAddButton = "xpath=//*[@id='aw_toolsAndInfo']//button[@class='sw-button ']";

    private String openPanelButton = "nav button[aria-label=Open]";
    private String openMenuButton = "div[class=sw-popup-contentContainer] li[aria-label=Open]";

    private String itemReviseMenuButton = "//li[@aria-label='Save As or Revise']";

    private String revisionTextfield = "input[name=item_revision_id]";

    private String revisionLabel = "span[data-locator=Revision]";

    public ItemsPage(Page page){
        this.page = page;
    }
    public boolean itemRevise(){
        page.locator(openPanelButton).click();
        page.locator(openMenuButton).click();
        page.locator(createNewPanelButton).click();
        page.locator(itemReviseMenuButton).click();
        page.waitForSelector(revisionTextfield).isEditable();
        String rev1 = page.locator(revisionLabel).textContent();
        System.out.println(rev1);
        page.locator(submenuAddButton).click();
        page.waitForSelector(revisionLabel).isVisible();
        page.waitForSelector(submenuAddButton).isHidden();
        String rev2 = page.locator(revisionLabel).textContent();
        System.out.println(rev2);
        return rev1 != rev2;
    }
}
