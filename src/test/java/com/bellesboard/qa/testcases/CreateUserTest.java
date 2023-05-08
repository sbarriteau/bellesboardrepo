package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class CreateUserTest extends TestBase{
  
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	
	public CreateUserTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();	
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
			  	
	  @Test(priority = 1)
	  public void createUserPage() {
		  System.out.println("Login to home Page to create new user");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Testim")).isDisplayed();
		  driver.findElement(By.partialLinkText("Testim")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Create User')]")).click();
		  
		  WebElement abilityToAccessSite = driver.findElement(By.name("siteaccess"));
		  Select accessValue = new Select(abilityToAccessSite);		  
		  accessValue.selectByValue("1");
		  
		  WebElement orgAdmin = driver.findElement(By.name("Admin"));
		  Select Admin = new Select(orgAdmin);		  
		  Admin.selectByValue("2");
		  
		  driver.findElement(By.xpath("//label[1]/div/ins")).click();
		  driver.findElement(By.xpath("//label[3]/div/ins")).click();
		  driver.findElement(By.xpath("//label[5]/div/ins")).click();
		  
		  driver.findElement(By.xpath("//div[2]/div/label/div/ins")).click(); //Display Settings selection
		  
		  WebElement sendWelcomeEmail = driver.findElement(By.name("send_welcome"));
		  Select sendWelcom = new Select(sendWelcomeEmail);		  
		  sendWelcom.selectByValue("1");
		  String usrname = "rahul5@mailcatch.com";
		  System.out.println("New User: "+usrname);		  
		  
		  driver.findElement(By.name("email")).sendKeys(usrname);
		  driver.findElement(By.name("firstname")).sendKeys("Rahul");
		  driver.findElement(By.name("lastname")).sendKeys("Kumar");
		  driver.findElement(By.name("title")).sendKeys("Mr.");
		  
		  WebElement selectTimeZone = driver.findElement(By.name("user_utc"));
		  Select selectTZone = new Select(selectTimeZone);		  
		  selectTZone.selectByValue("-5");		  
		  
		  driver.findElement(By.name("birthday")).sendKeys("05/12/1983");
		  
		  try {
			     driver.findElement(By.id("send")).click();
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(By.id("send")));
			  }
		  
		 // driver.findElement(By.id("send")).click();
		 
		 // driver.findElement(By.className("alert alert-success ProfileUpdated")).isDisplayed();
		  
		  driver.findElement(By.partialLinkText("Testim")).click();	
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		  
	  }
	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
