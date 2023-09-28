package com.expleo.playwrightdemoapp;

import java.awt.Toolkit;
import java.awt.Dimension;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int w = (int)screenSize.getWidth();
      int h = (int)screenSize.getHeight();

      Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(100));
      BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(w, h));
      Page page = context.newPage();
      page.navigate("http://s-inepnq-tc02p:3000/");
      page.setDefaultTimeout(120000);
      PlaywrightAssertions.setDefaultAssertionTimeout(120000);
      // Expect a title "to contain" a substring.
      assertThat(page).hasTitle(Pattern.compile("Teamcenter"));

            // create a locator
            Locator username = page.locator("xpath=//input[@name='username']");
            Locator pwd = page.locator("xpath=//input[@name='password']");
            Locator signInButton = page.locator("xpath=//button[@type='submit']");
            Locator profileAvatarImage = page.locator("xpath=//div[@class='flex-shrink sw-order-one']//button[@aria-label='Your Profile']");
            Locator signOutButton = page.locator("xpath=//*[@id='globalNavigationSideNav']//button[@aria-label='Sign Out']");

            //AW_ItemSearch
            Locator advanceSearchButton = page.locator("xpath=//a[@aria-label='Advanced Search']");
            Locator advanceSearchQueryName = page.locator("xpath=//textarea[@name='Name']");
            Locator advanceSearchQueryDatasetName = page.locator("xpath=//textarea[@name='DatasetName']"); 
            
            //TC1
            username.fill("Engineer_1");
            pwd.fill("Engineer_1");
            signInButton.click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("signin.png")));
            profileAvatarImage.click();
            signOutButton.click();

            //TC3
            username.fill("Engineer_1");
            pwd.fill("Engineer_1");
            signInButton.click();
            advanceSearchButton.click();
            page.getByPlaceholder("Select a search").click();
            page.locator("xpath=//div[@title='General...']").click();
            page.locator("xpath=//a[@title='Clear All']").click();
            advanceSearchQueryName.fill("test");
            page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=(//span[@title='test'])[1]")).isVisible();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FolderSearched.png")));
            profileAvatarImage.click();
            signOutButton.click();
            
            //TC5
            username.fill("Engineer_1");
            pwd.fill("Engineer_1");
            signInButton.click();
            advanceSearchButton.click();
            page.getByPlaceholder("Select a search").click();
            page.locator("xpath=//div[@title='Item...']").click();
            page.locator("xpath=//a[@title='Clear All']").click();
            advanceSearchQueryName.fill("test");
            page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=//span[@title='test']")).isVisible();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("ItemSearched.png")));
            profileAvatarImage.click();
            signOutButton.click();

            //TC11
            username.fill("Engineer_1");
            pwd.fill("Engineer_1");
            signInButton.click();
            advanceSearchButton.click();
            page.getByPlaceholder("Select a search").click();
            page.locator("xpath=//div[@title='Dataset Name']").click();
            page.locator("xpath=//a[@title='Clear All']").click();
            advanceSearchQueryDatasetName.fill("test");
            page.locator("xpath=//button[@class='sw-button ']//div[contains(text(),'Search')]").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=(//span[@title='test'])[1]")).isVisible();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("DatasetNameSearched.png")));
            profileAvatarImage.click();
            signOutButton.click();




      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("signout.png")));
    }
  }
}
