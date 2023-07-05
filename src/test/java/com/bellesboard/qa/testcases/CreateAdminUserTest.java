package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;
import com.bellesboard.qa.util.TestUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList; // import the ArrayList class
import java.util.List;

public class CreateAdminUserTest extends TestBase{
  
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String[] arrSplit;	
	String usrname;
	String pass;
	String loginPass;
	int cnt;
	int cont;
	
	public CreateAdminUserTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		initialization();	
		loginPage = new LoginPage();
		
		ClearGmail();
		loginPage.login(prop.getProperty("adminTestUser"), prop.getProperty("password"));
	}
	
	
				  	
	  @Test(priority = 1)
	  public void createUserPage() {
		  System.out.println("Login to home Page to create new user");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Welcome")).isDisplayed();
		  driver.findElement(By.partialLinkText("Welcome")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Create User')]")).click();
		  
		  WebElement abilityToAccessSite = driver.findElement(By.name("siteaccess"));
		  Select accessValue = new Select(abilityToAccessSite);		  
		  accessValue.selectByValue("1");//Site access ability yes
		  
		  WebElement orgAdmin = driver.findElement(By.name("Admin"));
		  Select Admin = new Select(orgAdmin);		  
		  Admin.selectByValue("2");//Admin permission
		  
		  driver.findElement(By.xpath("//label[1]/div/ins")).click();
		  driver.findElement(By.xpath("//label[3]/div/ins")).click();
		  driver.findElement(By.xpath("//label[5]/div/ins")).click();
		  
		  driver.findElement(By.xpath("//div[2]/div/label/div/ins")).click(); //Display Settings selection
		  
		  WebElement sendWelcomeEmail = driver.findElement(By.name("send_welcome"));
		  Select sendWelcom = new Select(sendWelcomeEmail);		  
		  sendWelcom.selectByValue("1");
		  usrname = prop.getProperty("NewUser");
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
				
		  driver.findElement(By.partialLinkText("Welcome")).click();	
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();		  
	  }
	  
	  @Test(priority = 2)
		public void CheckWelcomeMail() {
		  	driver.navigate().to("https://mail.google.com/");
		  	driver.findElement(By.xpath("//li/div/div/div/div[2]/div")).click();
		      //identify email
		     // WebElement l = driver.findElement(By.name("identifier"));
		     // l.sendKeys(usrname);
		     // WebElement b = driver.findElement(By.xpath("//span[contains(.,'Next')]"));
		      //b.click();
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      //identify password
		      pass = prop.getProperty("gmailPass");
		      WebElement p = driver.findElement(By.xpath("//input[@name='password']"));
		      p.sendKeys(pass);
		      
		      driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      pause(2000);
		      WebElement Weleml = driver.findElement(By.xpath("//td[5]/div/div/div/span/span"));
		     
		      try {
		    	  Weleml.click();
		    	}
		    	catch(org.openqa.selenium.StaleElementReferenceException ex)
		    	{
		    		pause(2000);
		    		Weleml.click();
		    	}
		      		      
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      pause(2000);
		      
		      loginPass =  driver.findElement(By.xpath("//li[contains(text(),'Your password:')]")).getText();
		      System.out.println("New Pass "+loginPass);
		      
		         
		    System.out.println("New Password line: "+loginPass);
		      arrSplit = loginPass.split(": ");
		      System.out.println("New Password: "+arrSplit[1]);
		     
		      tearDown();
	  }
	  
	  @Test(priority = 3)
		public void loginWithNewPass() {
			initialization();	
			loginPage = new LoginPage();
			System.out.println("New Password from gmail: "+arrSplit[1]);
			loginPage.login(usrname, arrSplit[1]);
			pause(1000);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.partialLinkText("Re-send Code")).isDisplayed();
			
			pause(8000);
			
				((JavascriptExecutor)driver).executeScript("window.open()");				

				    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				    driver.switchTo().window(tabs.get(1)); //switches to new tab
				    				    
				    driver.navigate().to("https://mail.google.com/"); //Open gmail URL
				
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
				      sub.sendKeys("BellesBoard Login Verification");
				      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				      
				      //Click on Search button		      
				      sub.sendKeys(Keys.ENTER);
				      pause(2000);
				      
				      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				      WebElement vThcnt = driver.findElement(By.xpath("//div[contains(@class, 'yW')]"));
				      String vid = vThcnt.getAttribute("id");
				      System.out.println("ID of email thread: "+vid);
				      WebElement emal = driver.findElement(By.xpath("//td[4]/div[2]/span[1]/span"));
				      
				      try {
				    	  emal.click();
						  } catch (Exception e) {
						     JavascriptExecutor executor = (JavascriptExecutor) driver;
						     executor.executeScript("arguments[0].click();", emal);
						  } 
				      								
				      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				      pause(2000);			     
				      WebElement vCodeValue = driver.findElement(By.xpath("//tbody//tr//td//h2"));
				   				     
				      String verificationCode = vCodeValue.getText();
				      														 
				      System.out.println("New Verification Code: "+verificationCode);
				      
				      WebElement logout=driver.findElement(By.cssSelector(".gb_k"));
				      logout.click();
				      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				      driver.switchTo().frame(2);
				      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				      WebElement signout=driver.findElement(By.xpath("//div[2]/span/a/span[2]/div/div"));
				      signout.click();
				      		      
				      driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);  		 
				      driver.close();
				      driver.switchTo().window(tabs.get(0));
				      WebElement sendVCode = driver.findElement(By.xpath("//input[@name='authCode']"));
				      sendVCode.sendKeys(verificationCode);//Enter verification code   
				      
				      WebElement submit = driver.findElement(By.xpath("//button[@name='btntwofactor']"));
				      submit.click();
				      
			//Wait for Login
			  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
				
			  Assert.assertEquals(driver.getTitle(), "Home Page");
			  driver.findElement(By.partialLinkText("Welcome")).isDisplayed();
			  driver.findElement(By.partialLinkText("Welcome")).click();		  
			  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		}
	  
	  @AfterClass
	  public void closeBrowser() {
		  tearDown();
	  }

}
