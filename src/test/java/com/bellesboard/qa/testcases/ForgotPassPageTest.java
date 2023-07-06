package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;
import com.bellesboard.qa.util.TestUtil;
import org.openqa.selenium.StaleElementReferenceException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

@Test(groups = {"smokeclass"})
public class ForgotPassPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String[] arrSplit;
	String  pwd;
	String countEmail;
	String resetPassLink;
	String paswd;
	int cnt = 1;
	
	public ForgotPassPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		
	}
		
	  	
	  @Test(groups={"smoke"}, priority = 1)
	  public void forgotPassPage() {
		  System.out.println("Forgot Password Page Test");
		  ClearGmail();
		  Assert.assertEquals(driver.getTitle(), "Login");
		  driver.findElement(By.id("forgot")).isDisplayed();
		  driver.findElement(By.id("forgot")).click(); //Click on Forgot Password link
					  
		  driver.findElement(By.id("email1")).isDisplayed();
		  driver.findElement(By.id("forgot_password_submit")).isDisplayed();
		 
		  driver.findElement(By.id("email1")).sendKeys(prop.getProperty("NewUser"));
		  driver.findElement(By.id("forgot_password_submit")).click();		  
		  
		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"forgot_good\"]/div/div");
		  driver.findElement(By.xpath("//*[@id=\"forgot_good\"]/div/div")).isDisplayed();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  pause(5000);
			
	  }
	  
	  @Test(groups={"smoke"}, priority = 2)
		public void CheckReseteMail() {
		  	driver.navigate().to("https://mail.google.com/"); //Open gmail URL
			//driver.get("https://accounts.google.com/signin");
		      //identify email
		      WebElement l = driver.findElement(By.name("identifier"));
		      l.sendKeys(prop.getProperty("NewUser")); //Enter email address
		      WebElement b = driver.findElement(By.xpath("//span[contains(.,'Next')]"));
		      b.click(); //Click on Next button
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      //identify password
		      WebElement p = driver.findElement(By.xpath("//input[@name='Passwd']"));
		      p.sendKeys(prop.getProperty("gmailPass"));//Enter Password
		      
		      driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();//Click on Next button
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		         
		      
		      try {
				     driver.findElement(By.xpath("//span[contains(text(),'BellesBoard - Password Reset')]")).click();
				  } catch (Exception e) {
				     JavascriptExecutor executor = (JavascriptExecutor) driver;
				     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'BellesBoard - Password Reset')]")));
				  }  
	    	  resetPassLink = driver.findElement(By.xpath("//html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/p[3]/a")).getAttribute("href");
	    	  
		      System.out.println("Reset Password link: "+resetPassLink);		    	  
		     
		      WebElement profileLink = driver.findElement(By.cssSelector(".gb_k"));
		      profileLink.click();
		   
		      driver.switchTo().frame(2);
		      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		      WebElement signout=driver.findElement(By.xpath("//div[2]/span/a/span[2]/div/div"));
		      signout.click();
		      
		      Boolean a = isAlertPresent();
		      
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      		      
		      //Open Reset Password URL
		      driver.navigate().to(resetPassLink);
		      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		      
		      paswd = RandomStringPwd();
		      pwd = "Te$t23"+paswd;
		      //Enter new password
		        driver.findElement(By.id("NewPassword")).isDisplayed();
	      		driver.findElement(By.id("NewPassword")).sendKeys(pwd);
	      		driver.findElement(By.id("NewPassword_submit")).click();
	      		driver.findElement(By.cssSelector("#NewPassword_good .alert")).isDisplayed();
	      		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);  		 
	      		pause(3000);
	  }
	  
	  @Test(groups={"smoke"}, priority = 3)
	  public void checkLoginWithNewPassword() {
				
			loginPage = new LoginPage();
			System.out.println("New Password: "+pwd);
			loginPage.login(prop.getProperty("NewUser"), pwd);
			pause(2000);
			System.out.println("User logged In with new Password Successfully!");
		}
	 	  	  
	 
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
