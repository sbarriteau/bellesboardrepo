package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;
import com.bellesboard.qa.util.TestUtil;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	
	public HomePageTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();	
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
		
	  	
	  @Test
	  public void homePage() {
		  System.out.println("Home Page Test");		  
		
		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
		  
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Welcome")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Home')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Meetings & Events')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Calendar')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Tasks')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Documents')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Dashboard')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Committees')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Board Members')]")).isDisplayed();
		  
		  driver.findElement(By.partialLinkText("Welcome")).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Profile')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		  
		  driver.findElement(By.name("btnLogin")).isDisplayed();
	  }
	  
	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
