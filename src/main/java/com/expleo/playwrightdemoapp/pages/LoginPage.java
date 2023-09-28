package com.expleo.playwrightdemoapp.pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;
    private String username = "input[name='username']";
    private String password = "input[name='password']";
    private String signInButton = "button[type='submit']";
    private String folderTile = "div[title=Folders]";
    private String profileAvatarImage = "xpath=//div[@class='flex-shrink sw-order-one']//button[@aria-label='Your Profile']";
    private String signOutButton = "xpath=//*[@id='globalNavigationSideNav']//button[@aria-label='Sign Out']";

    public LoginPage(Page page){
        this.page = page;
    }
    public boolean doLoginAndLogout(String user, String pwd){
        page.fill(username, user);
        page.fill(password, pwd);
        page.click(signInButton);
        page.waitForSelector(folderTile).isVisible();
        page.locator(profileAvatarImage).click();
        page.locator(signOutButton).click();
        return page.waitForSelector(username).isVisible();
    }

    public boolean doLogin(String user, String pwd){
        page.fill(username, user);
        page.fill(password, pwd);
        page.click(signInButton);
        return page.waitForSelector(folderTile).isVisible();
    }

    public boolean doLogout(){
        page.waitForSelector(profileAvatarImage).isEnabled();
        page.locator(profileAvatarImage).click();
        page.locator(signOutButton).click();
        return page.waitForSelector(username).isVisible();
    }

}
