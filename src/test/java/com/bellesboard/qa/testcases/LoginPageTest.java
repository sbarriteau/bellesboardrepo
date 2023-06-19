package com.bellesboard.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList; // import the ArrayList class
import java.util.concurrent.TimeUnit;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.LoginPage;
import com.bellesboard.qa.util.TestUtil;


public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	
	public LoginPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();		
	}
	
	//@Test
	public void LoginPageTitleTest() {
		String title = loginPage.verifyTitle();
		Assert.assertEquals(title, "Login");
	}
	
	@Test
	public void LoginTest() {
		loginPage.login(prop.getProperty("adminTestUser"), prop.getProperty("password"));
		 /*	 
		if(driver.findElement(By.xpath("//label[contains(text(),'Enter Security Code')]")) == null)
		{
			((JavascriptExecutor)driver).executeScript("window.open()");
			

			    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			    driver.switchTo().window(tabs.get(1)); //switches to new tab
			    //driver.get("https://www.facebook.com");
			    
			    //driver.switchTo().window(tabs.get(0)); // switch back to main screen        
			    //driver.get("https://www.news.google.com");
			    
			    driver.navigate().to("https://mail.google.com/"); //Open gmail URL
			
			    //identify email
			      WebElement l = driver.findElement(By.name("identifier"));
			      l.sendKeys(prop.getProperty("adminTestUser")); //Enter email address
			      WebElement b = driver.findElement(By.xpath("//span[contains(.,'Next')]"));
			      b.click(); //Click on Next button
			      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			      //identify password
			      WebElement p = driver.findElement(By.xpath("//input[@name='Passwd']"));
			      p.sendKeys(prop.getProperty("admingmailPass"));//Enter Password
			      
			      driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();//Click on Next button
			      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			      
			      //Search Password reset email by subject
			      WebElement sub = driver.findElement(By.xpath("//input[@name='q']"));
			      sub.sendKeys("BellesBoard Login Verification");
			      
			      //Click on Search button		      
			      sub.sendKeys(Keys.ENTER);
			      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			      String countEmail = driver.findElement(By.xpath("//tr[1]/td[4]/div[2]/span[2]")).getText();
			      System.out.println("Number of email thread: "+countEmail);
			    		      
			      WebElement eml = driver.findElement(By.xpath("//tr[1]/td[4]/div[2]/span[1]/span"));
			      eml.click();
			      								
			      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
					
			      int cnt=Integer.parseInt(countEmail);  
			    //html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td/div[2]/div[2]/div/div[3]/div[1]/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[1]/div[1]/table/tbody/tr/td/h2
			    //html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td/div[2]/div[2]/div/div[3]/div[2]/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[1]/div[1]/table/tbody/tr/td/h2
			    //html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td/div[2]/div[2]/div/div[3]/div[10]/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[1]/div[1]/table/tbody/tr/td/h2
			      
			      //div[26]/div/div/div/div/div/div[2]/div[3]/div[3]/div/div[2]/div/table/tbody/tr/td/p/a
			      String verificationCode = driver.findElement(By.xpath("//html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td/div[2]/div[2]/div/div[3]/div["+cnt+"]/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[1]/div[1]/table/tbody/tr/td/h2")).getText();
							      
			      System.out.println("New Verification Code: "+verificationCode);
			      		      
			      driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);  		 
			      
		  }
		 */
		 
		 
		 driver.findElement(By.partialLinkText("Welcome")).isDisplayed();
	}
	
	@AfterTest
	  public void closeBrowser() {
		  tearDown();
	  }
}
