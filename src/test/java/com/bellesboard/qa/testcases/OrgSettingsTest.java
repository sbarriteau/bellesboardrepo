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

public class OrgSettingsTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	
	public OrgSettingsTest() {
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
		  System.out.println("Login to verify Organization Settings page");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Vik_First")).isDisplayed();
		  driver.findElement(By.partialLinkText("Vik_First")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Organization Settings')]")).click();//Click on Organization Information link
		  driver.findElement(By.xpath("//h1[contains(text(),'Organization Settings')]")).isDisplayed();
		  driver.findElement(By.xpath("//ol/li/a[contains(text(),'Home')]")).isDisplayed();
		  driver.findElement(By.xpath("//li[contains(text(),'Organization Settings')]")).isDisplayed();
		  driver.findElement(By.xpath("//h2[contains(text(),'Organization Settings')]")).isDisplayed();
		  		  
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Meeting Reminders')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Time Line')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Email')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/strong[contains(text(),'Mobile')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'All Meetings / Events - Show # Of Entries')]")).isDisplayed();
		  
		  driver.findElement(By.xpath("//div/label[contains(text(),'All Meetings / Events - Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'All Meetings / Events - Date Range')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Single Meeting / Event - Show # Of Attendees')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Single Meetings / Events - Attendee Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Single Meeting / Event - Show # Of Files')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Single Meetings / Events - File Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Tasks - Show # Of Entries')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Tasks - Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Documents - Show # Of Entries')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Documents - Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Committees - Show # Of Entries')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Committees - Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Board Members - Show # Of Entries')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Board Members - Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Messages - Show # Of Entries')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Messages - Default Sort')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Announcements - Show # Of Entries')]")).isDisplayed();
		  driver.findElement(By.xpath("//div/label[contains(text(),'Announcements - Default Sort')]")).isDisplayed();
		  
		  driver.findElement(By.xpath("//div/button[contains(text(),'Update Settings')]")).isDisplayed(); //Assert Update Settings button
		  
		  driver.findElement(By.partialLinkText("Vik_First")).click();	
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
  }
	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}