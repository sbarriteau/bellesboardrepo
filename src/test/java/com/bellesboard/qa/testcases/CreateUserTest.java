package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;
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

public class CreateUserTest extends TestBase{
  
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String[] arrSplit;
	//String usrname = "testrahul5@mailcatch.com";
	String usrname = "bellestest@gmail.com";
	String pass = "Tester@1234";
	
	public CreateUserTest() {
		super();
	}
	
	@BeforeClass
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
		      
		      driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Welcome to BellesBoard!");
		      
		      driver.findElement(By.cssSelector(".gb_Le > svg")).click();
	  
		      driver.findElement(By.xpath("//div[2]/span/span/span")).click();
		      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		      String loginPass = driver.findElement(By.xpath("//ol/li[3]")).getText();
		      System.out.println("New Password line: "+loginPass);
		      arrSplit = loginPass.split(": ");
		      System.out.println("New Password: "+arrSplit[1]);
		      driver.findElement(By.xpath("//div[@id=':4']/div[3]/div/div/div[2]/div[3]/div")).click();
		      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		      tearDown();
	  }
	  
	  @Test(priority = 3)
		public void loginWithNewPass() {
			initialization();	
			loginPage = new LoginPage();
			loginPage.login(usrname, arrSplit[1]);
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
