package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;
import com.bellesboard.qa.util.TestUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class ForgotPassPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String[] arrSplit;
	String usrname = "bellestest@gmail.com";
	String pass = "Tester@1234";
	
	public ForgotPassPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();		
	}
		
	  	
	  @Test(groups = {"smoke"}, priority = 1)
	  public void forgotPassPage() {
		  System.out.println("Forgot Password Page Test");
		
		  Assert.assertEquals(driver.getTitle(), "Login");
		  driver.findElement(By.id("forgot")).isDisplayed();
		  driver.findElement(By.id("forgot")).click(); //Click on Forgot Password link
					  
		  driver.findElement(By.id("email1")).isDisplayed();
		  driver.findElement(By.id("forgot_password_submit")).isDisplayed();
		 
		  driver.findElement(By.id("email1")).sendKeys(usrname);
		  driver.findElement(By.id("forgot_password_submit")).click();		  
		  
		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"forgot_good\"]/div/div");
		  driver.findElement(By.xpath("//*[@id=\"forgot_good\"]/div/div")).isDisplayed();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
	  }
	  
	  @Test(groups = {"smoke"}, priority = 2)
		public void CheckWelcomeMail() {
		  	driver.navigate().to("https://mail.google.com/");
			//driver.get("https://accounts.google.com/signin");
		      //identify email
		      WebElement l = driver.findElement(By.name("identifier"));
		      l.sendKeys(usrname);
		      WebElement b = driver.findElement(By.xpath("//span[contains(.,'Next')]"));
		      b.click();
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      //identify password
		      WebElement p = driver.findElement(By.xpath("//input[@name='Passwd']"));
		      p.sendKeys(pass);
		      
		      driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      
		      driver.findElement(By.xpath("//input[@name='q']")).sendKeys("BellesBoard - Password Reset");
		      
		      driver.findElement(By.cssSelector(".gb_Le > svg")).click();
	  
		      driver.findElement(By.xpath("//div[2]/span/span/span")).click();
		      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		      String resetPassLink = driver.findElement(By.xpath("//td/p/a")).getAttribute("href");
				
		      //String resetPasslink = driver.findElement(By.xpath("//*[@id=\":nr\"]/div[2]/div[1]/table/tbody/tr/td/p[3]")).getText();
		      System.out.println("New Password link: "+resetPassLink);
		      
		      driver.findElement(By.xpath("//div[@id=':4']/div[3]/div/div/div[2]/div[3]/div")).click();
		      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		      
		      driver.get(resetPassLink);
	  }
	  
	  
	  /*
	  @Test(priority = 2)
	  public void verifyEmail() {
		  loginPage = new LoginPage();
		  driver.get("http://mailcatch.com/en/disposable-email");
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[1]")).sendKeys(usrname);
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[2]")).click();
		  
		  //Wait for Login
		  explicitWaitForElement("//a[contains(text(),'BellesBoard - Password Reset')]");
			
		  driver.findElement(By.xpath("//a[contains(text(),'BellesBoard - Password Reset')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'BellesBoard - Password Reset')]")).click();
		 		  
		  driver.switchTo().frame("emailframe");
		 			  
		  String resetPassLink = driver.findElement(By.partialLinkText("https://app.bellesboard.com/index.cfm?p=login&fpwd=")).getText();
      		//System.out.println(resetPassLink);
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[1]")).sendKeys(usrname);
		  driver.findElement(By.xpath("//*[@id=\"mailboxform\"]/input[2]")).click();
		  driver.findElement(By.xpath("//*[@id=\"mailsList\"]/table/tbody/tr[3]/td[3]/a/img")).click();
		  
      		driver.get(resetPassLink);
      	      		
      		String pass = RandomStringPwd();
      		System.out.println("New Password: "+pass);
      		
      		driver.findElement(By.id("NewPassword")).isDisplayed();
      		driver.findElement(By.id("NewPassword")).sendKeys(pass);
      		driver.findElement(By.id("NewPassword_submit")).click();
      		
     		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
         		
  		  loginPage.login(usrname, pass);
  		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); 		
  		
	  }
	  	  
	  	  */
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
