package com.Selenium_for_intellij;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Main
{

    private WebDriver wd;
    private String url;
    private String usernMail;
    private String userPassword;

    @Before
    public void setUp()
    {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        wd = new FirefoxDriver();
        url = "https://www.tidio.com/panel/login";
        usernMail = "joannakorzen1@wp.pl";
        userPassword = "Kamila1212";
    }

    @Test
    public void loginNegative()
    {
        wd.get(url);
        WebElement loginName = wd.findElement(By.cssSelector("[placeholder='Email']"));
        loginName.clear();
        loginName.sendKeys(usernMail);

        WebElement passName = wd.findElement(By.cssSelector("[placeholder='Password']"));
        passName.clear();
        passName.sendKeys(userPassword);

       wd.findElement(By.cssSelector("[stype='submit']" )).click();
    }

    @After
    public void tearDown()
    {
        wd.quit();
    }






}