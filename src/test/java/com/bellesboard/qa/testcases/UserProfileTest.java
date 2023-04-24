package com.bellesboard.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;

public class UserProfileTest extends TestBase{
 
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	
	@BeforeMethod
	public void setUp() {
		initialization();	
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
		@Test
	  public void userProfilePage() {
			System.out.println("Verify user profile page");
			 try {
				    Thread.sleep(5000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			  Assert.assertEquals(driver.getTitle(), "Home Page");
			  driver.findElement(By.partialLinkText("Testim")).isDisplayed();
			  driver.findElement(By.partialLinkText("Testim")).click();		
			  
			  driver.findElement(By.xpath("//a[contains(text(),'Profile')]")).isDisplayed();
			  driver.findElement(By.xpath("//a[contains(text(),'Profile')]")).click();
			  driver.findElement(By.xpath("//h2[contains(text(),'Edit User')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Email Address')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Password')]")).isDisplayed();
			  driver.findElement(By.xpath("//div/div/span/button/i")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'First Name')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Last Name')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Board Title')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Time Zone')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Birthday')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Profile Picture')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Mailing Address')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Organization/Company Name')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Mailing Address')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'City')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'State')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Zipcode')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Job Title')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Office Phone')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Mobile Phone')]")).isDisplayed();
			  driver.findElement(By.xpath("//label[contains(text(),'Home Phone')]")).isDisplayed();
			  driver.findElement(By.id("send")).isDisplayed();			  
	  }
	
	 @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	
}
