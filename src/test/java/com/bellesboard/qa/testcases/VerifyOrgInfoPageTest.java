package com.bellesboard.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;

public class VerifyOrgInfoPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	
	public VerifyOrgInfoPageTest() {
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
		  System.out.println("Login to verify Organization Information page");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Welcome")).isDisplayed();
		  driver.findElement(By.partialLinkText("Welcome")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Organization Information')]")).click();//Click on Organization Information link
		  driver.findElement(By.xpath("//h2[contains(text(),'Organization Information')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Organization Admins')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Total Users')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Total Space Used:')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Document Signing')]")).isDisplayed();
		  driver.findElement(By.xpath("//td/strong[contains(text(),'Total')]")).isDisplayed();
		  
		  driver.findElement(By.xpath("//h2[contains(text(),'Global User Settings')]")).isDisplayed();
		  driver.findElement(By.xpath("//div[contains(text(),'Meeting / Event View:')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/button[contains(text(),'Update')]")).isDisplayed();
		  driver.findElement(By.xpath("//h2[contains(text(),'Export Users')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Export All Users')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Export Board Members')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Export Comittees')]")).isDisplayed();
		  
		  driver.findElement(By.partialLinkText("Welcome")).click();	
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
  }
	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
