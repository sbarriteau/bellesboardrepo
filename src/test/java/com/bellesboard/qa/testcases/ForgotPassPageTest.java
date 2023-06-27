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
		  pause(3000);
			
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
		      
		      //Search Password reset email by subject
		      WebElement sub = driver.findElement(By.xpath("//input[@name='q']"));
		      sub.sendKeys("BellesBoard - Password Reset");
		      
		      //Click on Search button		      
		      sub.sendKeys(Keys.ENTER);
		      try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      
		      WebElement Thcnt = driver.findElement(By.xpath("//div[contains(@class, 'yW')]"));
		      String id1 = Thcnt.getAttribute("id");
		      System.out.println("ID of email thread: "+id1);
		      WebElement eml = driver.findElement(By.xpath("//*[@id=\'"+id1+"\']/span[1]/span"));
		            
		      if(driver.findElements(By.xpath("//*[@id=\'"+id1+"\']/span[2]")).size() != 0)
		      {
		    	  countEmail = driver.findElement(By.xpath("//*[@id=\'"+id1+"\']/span[2]")).getText();
		    	  
		    	  int cnt=Integer.parseInt(countEmail); 		      
					
			      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
			      try {
			    	  eml.click();
					  } catch (Exception e) {
					     JavascriptExecutor executor = (JavascriptExecutor) driver;
					     executor.executeScript("arguments[0].click();", eml);
					  } 
			      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
			      
			      new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//div["+cnt+"]/div/div/div/div/div/div[2]/div[3]/div[3]/div/div[2]/div/table/tbody/tr/td/p/a")));
			     			      
			      resetPassLink = driver.findElement(By.xpath("//div["+cnt+"]/div/div/div/div/div/div[2]/div[3]/div[3]/div/div[2]/div/table/tbody/tr/td/p/a")).getAttribute("href");   		      
			      
		      }
		      else
		      {       	  
		    	  try {
					     driver.findElement(By.xpath("//*[@id=\'"+id1+"\']/span/span")).click();
					  } catch (Exception e) {
					     JavascriptExecutor executor = (JavascriptExecutor) driver;
					     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\'"+id1+"\']/span/span")));
					  }  
		    	  resetPassLink = driver.findElement(By.xpath("//html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/p[3]/a")).getAttribute("href");
				  
		      }  
		      System.out.println("Reset Password link: "+resetPassLink);		    	  
		     
		      WebElement logout=driver.findElement(By.cssSelector(".gb_k"));
		      logout.click();
		   
		      driver.switchTo().frame("account");
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      WebElement signout=driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div/div[2]/div[2]/span/a/span[2]/div/div"));
		      signout.click();
		      
		      
		      driver.switchTo().frame(0);
		      
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
		      
	  }
	  
	  @Test(groups={"smoke"}, priority = 3)
	  public void checkLoginWithNewPassword() {
				
			loginPage = new LoginPage();
			System.out.println("New Password: "+pwd);
			loginPage.login(prop.getProperty("NewUser"), pwd);
			
			System.out.println("User logged In with new Password Successfully!");
		}
	 	  	  
	 
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
