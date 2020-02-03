package com.Selenium_for_intellij;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Main
{
    private WebDriver wd;
    private String url;
    private String userMail;
    private String userPassword;
    private String urlSubpage;
    private String urlConversation;
    private String randomMail;
    private String randomText;

    protected String getSaltString() 
    {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;


    }

    @Before
    public void setUp()
    {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        wd = new FirefoxDriver();
        url = "https://www.tidio.com/panel/login";
        urlSubpage = "https://widget-v4.tidiochat.com/preview.html?code=cpteevo4sxz2by18zulob7o7wgfscjtk";
        urlConversation = "https://www.tidio.com/panel/conversations/";
        userMail = "kamkaa24@wp.pl";
        userPassword = "Kamila1212";
        randomMail = "@wp.pl";
        randomText = "jfdsdfsd";
    }

    @Test
    public void loginPositive() throws InterruptedException 
    {
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(wd,10);
        
        wd.get(url);
        WebElement loginName = wd.findElement(By.cssSelector("[placeholder='Email']"));
        loginName.clear();
        loginName.sendKeys(userMail);

        WebElement passName = wd.findElement(By.cssSelector("[placeholder='Password']"));
        passName.clear();
        passName.sendKeys(userPassword);

        wd.findElement(By.cssSelector("[type='submit']" )).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='ion-ios-chatbubble']")));
        Assert.assertTrue(wd.getPageSource().contains("Squeeze the most value out of Tidio"));

        wd.get(urlSubpage);
        wd.switchTo().frame("tidio-chat-iframe");
        wd.findElement(By.xpath("//button[@title='Yes, please!']")).click();
        WebElement message = wd.findElement(By.cssSelector("[placeholder='Hit the buttons to respond']"));
        wd.findElement(By.xpath("//button[@title='E-mail']")).click();
        message.clear();
        message.sendKeys(getSaltString()+randomMail);
        Thread.sleep(5555);
        message.sendKeys(Keys.ENTER);
        Thread.sleep(5555);
        message.sendKeys(randomText);
        message.sendKeys(Keys.ENTER);
        Thread.sleep(5555);
        wd.findElement(By.cssSelector("[class='user-data-modal-submit']")).click();

        wd.get(urlConversation);
        wd.findElement(By.xpath("//*[contains(text(),'" + randomText + "')]"));
        Assert.assertTrue((wd.getPageSource().contains(randomText)));
        Thread.sleep(5555);
    }

    @After
    public void tearDown()
    {
        wd.quit();
    }
}