package com.expleo.playwrightdemoapp.pages;

import java.nio.file.Paths;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.aventstack.extentreports.Status.INFO;
import static com.expleo.playwrightdemoapp.Factory.PlaywrightFactory.takeScreenshot;
import static com.expleo.playwrightdemoapp.listeners.ExtentReportListener.test;

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
        test.get().log(INFO,"Enter "+user+" in username field", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),"username").build());
        page.fill(password, pwd);
        test.get().log(INFO,"Enter password in password field", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),"password").build());
        page.click(signInButton);
        test.get().log(INFO,"Click on SignIn button", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),"Signinbutton").build());
        page.waitForSelector(folderTile).isVisible();
        page.locator(profileAvatarImage).click();
        test.get().log(INFO,"Click on Profile", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),"profile").build());
        page.locator(signOutButton).click();
        test.get().log(INFO,"Click on Sing out option", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),"signout").build());
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
