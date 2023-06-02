package com.bellesboard.qa.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Admin_CreateNewUserTest extends TestBase{
  
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String usrname = "testnewadminuser@mailcatch.com";
	String adminPass = "Tester@1234";
	public Admin_CreateNewUserTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();	
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
			  	
	  @Test(priority = 1)
	  public void createAdminUserPage() {
		  System.out.println("Login to home Page to create new user");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Vik_First")).isDisplayed();
		  driver.findElement(By.partialLinkText("Vik_First")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Create User')]")).click();
		  
		  WebElement abilityToAccessSite = driver.findElement(By.name("siteaccess"));
		  Select accessValue = new Select(abilityToAccessSite);		  
		  accessValue.selectByValue("0");//Site access ability yes
		  accessValue.selectByValue("1");//Site access ability yes
		  
		  WebElement orgAdmin = driver.findElement(By.name("Admin"));
		  Select Admin = new Select(orgAdmin);		  
		  Admin.selectByValue("1");//Org Admin permission
		  		  
		  WebElement sendWelcomeEmail = driver.findElement(By.name("send_welcome"));
		  Select sendWelcom = new Select(sendWelcomeEmail);		  
		  sendWelcom.selectByValue("0");
		  //String usrname = "newadminuser@mailcatch.com";
		  System.out.println("New User: "+usrname);		  
		  
		  driver.findElement(By.name("email")).sendKeys(usrname);
		  driver.findElement(By.name("firstname")).sendKeys("New");
		  driver.findElement(By.name("lastname")).sendKeys("Admin");
		  driver.findElement(By.name("title")).sendKeys("Mr.");
		  
		  WebElement selectTimeZone = driver.findElement(By.name("user_utc"));
		  Select selectTZone = new Select(selectTimeZone);		  
		  selectTZone.selectByValue("-5");		  //Select Timezone
		  
		  driver.findElement(By.name("birthday")).sendKeys("05/12/1983");//Enter Birthday
		  
		  WebElement selectMailingAddress = driver.findElement(By.name("company_home"));
		  Select selectMailingAdd = new Select(selectMailingAddress);		  
		  selectMailingAdd.selectByValue("0"); //Select Mailing Address
		  
		  driver.findElement(By.name("office_phone")).sendKeys("+158585858"); //Enter Office Phone
		  driver.findElement(By.name("mobile_phone")).sendKeys("+185858585"); //Enter Mobile phone
		  driver.findElement(By.name("home_phone")).sendKeys("+188885555"); //Enter Home phone
		  
		  try {
			     driver.findElement(By.id("send")).click();
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(By.id("send")));
			  }
		  
		  driver.findElement(By.partialLinkText("Vik_First")).click();	
		
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		  
	  }
	  
	  @Test(priority = 2)
	  public void changeAdminUserPass() {
		  System.out.println("Login to change admin user password");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Vik_First")).isDisplayed();
		  driver.findElement(By.partialLinkText("Vik_First")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  
		  driver.findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();	//Click on Manage Users
		  
		  //Verify new admin user created
		  String originalTitle = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[1]/div[1]/div/div[1]/h2")).getText();
		  
		  String expectedTitle = "Organization Information";
		  Assert.assertEquals(originalTitle, expectedTitle);
		  driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0_filter\"]/label/input")).sendKeys("newadminuser@mailcatch.com");

		  String searchedUser = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[3]")).getText();
		  													
		  String expSearchedUser = usrname;
		  Assert.assertEquals(searchedUser, expSearchedUser);
		  driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[9]/a/i")).click();
		  //Assert page elements
		  String editUsr = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/h2")).getText();		  
		  String editUser = "Edit User";
		  Assert.assertEquals(editUsr, editUser);
		  
		 /* Select select = new Select(someElement);
		  String option = select.getFirstSelectedOption().getText();
		  Assert.assertEquals("Alabama", option); */
		  
		  //Assert user email
		  String email = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[5]/div[1]/div[1]/input")).getAttribute("value");
		  Assert.assertEquals(usrname, email);
		  System.out.println("Useremail: "+email);
		  
		  //Enter new password
		  driver.findElement(By.name("password")).sendKeys(adminPass);
		  
		  //Click on Update Profile button
		  try {
			     driver.findElement(By.id("send")).click();
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(By.id("send")));
			  }
		  
		  driver.findElement(By.partialLinkText("Vik_First")).click();	
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		}
	   
	  @Test(priority = 3)
	  public void newAdminUserLogin() {			
			loginPage.login(usrname, adminPass);
		}
	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }

}
