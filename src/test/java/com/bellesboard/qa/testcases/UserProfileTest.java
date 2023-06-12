package com.bellesboard.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;

@Test(groups = {"smokeclass"})
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
			System.out.println("Verify admin user profile page");
			
			//Wait for Login
			  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			  Assert.assertEquals(driver.getTitle(), "Home Page");//Assert home page title
			  driver.findElement(By.partialLinkText("Welcome")).isDisplayed();//Assert logged in user name
			  driver.findElement(By.partialLinkText("Welcome")).click();		//Click on user name link
			  
			  driver.findElement(By.xpath("//a[contains(text(),'Profile')]")).isDisplayed(); //Assert Profile link 
			  driver.findElement(By.xpath("//a[contains(text(),'Profile')]")).click(); //Click on Profile link
			  driver.findElement(By.xpath("//h2[contains(text(),'Edit User')]")).isDisplayed(); //Assert Edit User form 
			  driver.findElement(By.xpath("//label[contains(text(),'Email Address')]")).isDisplayed(); //Assert Email Address field label
			  //String text = driver.findElement(By.name("email")).getText();
			  //assertTrue("The text doesn't contains the value", text.contains("value"));
			  String aa = driver.findElement(By.xpath("//*[@id=\'UpdateMyProfile\']/div[1]/div[1]/div[1]/input")).getAttribute("value");
			 // System.out.println(aa);
			 
			  Assert.assertEquals(aa, "vikashadmintest@mailcatch.com"); //Verify the email address of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Password')]")).isDisplayed(); //Assert Password field
			  String pp = driver.findElement(By.xpath("//*[@id=\"password\"]")).getAttribute("name");
			 // System.out.println(pp);
			  Assert.assertEquals(pp, "password"); //Verify the Password text field of the logged in user
			  
			  driver.findElement(By.xpath("//div/div/span/button/i")).isDisplayed(); //Assert View/hide password field
			  driver.findElement(By.xpath("//label[contains(text(),'First Name')]")).isDisplayed(); //Assert First Name
			  String fname = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/div[1]/div[3]/input")).getAttribute("value");
			 // System.out.println(fname);
			  Assert.assertEquals(fname, "Vik_First"); //Verify the First Name field of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Last Name')]")).isDisplayed(); //Assert Last Name
			  
			  String lname = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/div[1]/div[4]/input")).getAttribute("value");
			  //System.out.println(lname);
			  Assert.assertEquals(lname, "Vik_last"); //Verify the Last Name field of the logged in user
			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Board Title')]")).isDisplayed(); //Assert Board Title field
			  String brdtitle = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/div[1]/div[5]/input")).getAttribute("value");
			 // System.out.println(brdtitle);
			  Assert.assertEquals(brdtitle, ""); //Verify the Board title field of the logged in user
			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Time Zone')]")).isDisplayed(); //Assert Time zone field
			  String timezone = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/div[1]/div[6]/select")).getAttribute("name");
			  //System.out.println(timezone);
			  Assert.assertEquals(timezone, "user_utc"); //Verify the Time zone value field of the logged in user
			  			  
			  driver.findElement(By.xpath("//label[contains(text(),'Birthday')]")).isDisplayed(); //Assert Birthday field
			  String birthdate = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/div[1]/div[7]/div[1]/div/input")).getAttribute("value");
			  System.out.println(birthdate);
			  Assert.assertEquals(birthdate, ""); //Verify the Birthday value field of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Profile Picture')]")).isDisplayed();//Assert Profile Picture field
			  String profilePic = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/div[2]/div/img")).getAttribute("src");
			  //System.out.println(profilePic);
			  Assert.assertEquals(profilePic, "https://staging.bellesboard.com/org_files/circle1.png"); //Verify the Profile picture field of the logged in user
			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Mailing Address')]")).isDisplayed(); //Assert Mailing Address field
			  String mailingAdd = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[2]/select")).getAttribute("name");
			  //System.out.println(mailingAdd);
			  Assert.assertEquals(mailingAdd, "company_home"); //Verify the Profile picture field of the logged in
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization/Company Name')]")).isDisplayed(); //Assert Organization/Company Name field
			  String orgName = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[3]/input")).getAttribute("name");
			  //System.out.println(orgName);
			  Assert.assertEquals(orgName, "company_name"); //Verify the Profile picture field of the logged in
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Mailing Address')]")).isDisplayed();//Assert Mailing Address field
			  driver.findElement(By.xpath("//label[contains(text(),'City')]")).isDisplayed();//Assert City field
			  driver.findElement(By.xpath("//label[contains(text(),'State')]")).isDisplayed();//Assert State field
			  driver.findElement(By.xpath("//label[contains(text(),'Zipcode')]")).isDisplayed();//Assert Zipcode field
			  driver.findElement(By.xpath("//label[contains(text(),'Job Title')]")).isDisplayed();//Assert Job Title field
			  driver.findElement(By.xpath("//label[contains(text(),'Office Phone')]")).isDisplayed();//Assert Office Phone field
			  driver.findElement(By.xpath("//label[contains(text(),'Mobile Phone')]")).isDisplayed();//Assert Mobile phone field
			  driver.findElement(By.xpath("//label[contains(text(),'Home Phone')]")).isDisplayed();//Assert Home Phone field
			  driver.findElement(By.id("send")).isDisplayed();	//Assert Update button
			  
			  driver.findElement(By.partialLinkText("Welcome")).click();		//Click on user name link
			  driver.findElement(By.xpath("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[3]/nav/ul/li/ul/li[10]/a")).click();
			  
	  }
	
		@AfterMethod
		  public void closeBrowser() {
			  tearDown();
		  }
	
}
