package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;
import com.bellesboard.qa.util.TestUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class ForgotPassPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String tempEmail;
	public ForgotPassPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();		
	}
		
	  	
	  @Test(priority = 1)
	  public void forgotPassPage() {
		  System.out.println("Forgot Password Page Test");
		
		  Assert.assertEquals(driver.getTitle(), "Login");
		  driver.findElement(By.id("forgot")).isDisplayed();
		  driver.findElement(By.id("forgot")).click(); //Click on Forgot Password link
					  
		  driver.findElement(By.id("email1")).isDisplayed();
		  driver.findElement(By.id("forgot_password_submit")).isDisplayed();
		  //driver.findElement(By.id("email1")).sendKeys("vikash2060t@gmail.com");
		  
		  //tempEmail = "rahul"+ generateEmailwithTimeStamp() + "@mailcatch.com";
		  tempEmail = "rahul5@mailcatch.com";

		  driver.findElement(By.id("email1")).sendKeys(tempEmail);
		  driver.findElement(By.id("forgot_password_submit")).click();		  
		  
		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"forgot_good\"]/div/div");
		  driver.findElement(By.xpath("//*[@id=\"forgot_good\"]/div/div")).isDisplayed();
			
	  }
	  
	  @Test(priority = 2)
	  public void verifyEmail() {
		  loginPage = new LoginPage();
		  driver.get("http://mailcatch.com/en/disposable-email");
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[1]")).sendKeys(tempEmail);
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[2]")).click();
		  
		  //Wait for Login
		  explicitWaitForElement("//a[contains(text(),'BellesBoard - Password Reset')]");
			
		  driver.findElement(By.xpath("//a[contains(text(),'BellesBoard - Password Reset')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'BellesBoard - Password Reset')]")).click();
		 		  
		  driver.switchTo().frame("emailframe");
		 			  
		  String resetPassLink = driver.findElement(By.partialLinkText("https://app.bellesboard.com/index.cfm?p=login&fpwd=")).getText();
      		//System.out.println(resetPassLink);
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[1]")).sendKeys(tempEmail);
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[2]")).click();
		  driver.findElement(By.xpath("//*[@id=\"mailsList\"]/table/tbody/tr[3]/td[3]/a/img")).click();
		  
      		driver.get(resetPassLink);
      	      		
      		String pass = RandomStringPwd();
      		System.out.println("New Password: "+pass);
      		
      		driver.findElement(By.id("NewPassword")).isDisplayed();
      		driver.findElement(By.id("NewPassword")).sendKeys(pass);
      		driver.findElement(By.id("NewPassword_submit")).click();
      		
     		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
         		
  		  loginPage.login(tempEmail, pass);
  		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); 		
  		
	  }
	  	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
