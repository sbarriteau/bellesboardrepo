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

public class OrgProfileTest extends TestBase{
 
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
	  public void orgProfilePage() {
			System.out.println("Verify Organization user profile page");
			//Wait for Login
			  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			  
			  Assert.assertEquals(driver.getTitle(), "Home Page");//Assert home page title
			  driver.findElement(By.partialLinkText("Testim")).isDisplayed();//Assert logged in user name
			  driver.findElement(By.partialLinkText("Testim")).click();		//Click on user name link
			  
			  driver.findElement(By.xpath("//a[contains(text(),'Organization Profile')]")).isDisplayed(); //Assert Profile link 
			  driver.findElement(By.xpath("//a[contains(text(),'Organization Profile')]")).click(); //Click on Profile link
			  driver.findElement(By.xpath("//h2[contains(text(),'Organization Profile')]")).isDisplayed(); //Assert Edit User form 
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Name')]")).isDisplayed(); //Assert Email Address field label
			  
			  String aa = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[1]/input")).getAttribute("value");
			  //System.out.println(aa);
			 
			  Assert.assertEquals(aa, "Advocates for Older Adults"); //Verify the email address of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Address')]")).isDisplayed(); //Assert Password field
			  String orgAdd = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[2]/input")).getAttribute("value");
			  //System.out.println(orgAdd);
			  Assert.assertEquals(orgAdd, "164 Colombo Blvd"); //Verify the Password text field of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization City')]")).isDisplayed(); //Assert View/hide password field
			  			  
			  String orgCity = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[3]/div/div[1]/input")).getAttribute("value");
			  //System.out.println(orgCity);
			  Assert.assertEquals(orgCity, "Bethpage"); //Verify the First Name field of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization State')]")).isDisplayed(); //Assert Last Name
			  
			  String orgState = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[3]/div/div[2]/input")).getAttribute("value");
			  //System.out.println(orgState);
			  Assert.assertEquals(orgState, "NY"); //Verify the Last Name field of the logged in user
			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Zipcode')]")).isDisplayed(); //Assert Board Title field
			  String orgZip = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[3]/div/div[3]/input")).getAttribute("value");
			  //System.out.println(orgZip);
			  Assert.assertEquals(orgZip, "11584"); //Verify the Board title field of the logged in user
			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Phone Number')]")).isDisplayed(); //Assert Time zone field
			  String orgPh = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[4]/input")).getAttribute("value");
			  //System.out.println(orgPh);
			  Assert.assertEquals(orgPh, "516-886-7250"); //Verify the Time zone value field of the logged in user
			  			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Fax Number')]")).isDisplayed(); //Assert Birthday field
			  String orgFax = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[5]/input")).getAttribute("value");
			 // System.out.println(orgFax);
			  Assert.assertEquals(orgFax, "516-886-7251"); //Verify the Birthday value field of the logged in user
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Website')]")).isDisplayed();//Assert Profile Picture field
			  String orgWebsite = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[6]/input")).getAttribute("value");
			  //System.out.println(orgWebsite);
			  Assert.assertEquals(orgWebsite, "www.AdvocatesForOlderAdults.org"); //Verify the Profile picture field of the logged in user
			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Time Zone')]")).isDisplayed(); //Assert Mailing Address field
			  String orgTimezone = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[7]/select/option[2]")).getAttribute("value");
			  //System.out.println(orgTimezone);
			  Assert.assertEquals(orgTimezone, "-4"); //Verify the Profile picture field of the logged in
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Founding Year')]")).isDisplayed(); //Assert Organization/Company Name field
			  String orgFoundYear = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[8]/select/option[21]")).getAttribute("value");
			  //System.out.println(orgFoundYear);
			  Assert.assertEquals(orgFoundYear, "2004"); //Verify the Profile picture field of the logged in
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Primary Organization Administrator Email')]")).isDisplayed();//Assert Mailing Address field
			  String orgAdminEmail = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[9]/input")).getAttribute("name");
			  //System.out.println(orgAdminEmail);
			  Assert.assertEquals(orgAdminEmail, "admin_email"); //Verify the Profile picture field of the logged in
			  			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Logo')]")).isDisplayed();//Assert City field
			  String orgLogo = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[10]/input")).getAttribute("name");
			  //System.out.println(orgLogo);
			  Assert.assertEquals(orgLogo, "company_logo"); //Verify the Profile picture field of the logged in			  
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Issue Area')]")).isDisplayed();//Assert State field
			  String orgIssueArea = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[11]/select")).getAttribute("value");
			  //System.out.println(orgIssueArea);
			  Assert.assertEquals(orgIssueArea, "Aging & Seniors");
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Mission Statement')]")).isDisplayed();//Assert Zipcode field
			  String orgMissionStatement = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[12]/div/div[3]/div[2]/p[1]/span")).getText();
			  //System.out.println(orgMissionStatement);
			  Assert.assertEquals(orgMissionStatement, "Advocates For Older Adults helps older adults with the everyday challenges of food, clothing, shelter and social engagement to provide meaningful and constructive quality of life.");
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Vision Statement')]")).isDisplayed();//Assert Job Title field
			  String orgVisionStatement = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[13]/div/div[3]/div[2]/p[2]/span")).getText();
			  //System.out.println(orgVisionStatement);
			  Assert.assertEquals(orgVisionStatement, "Our vision "
			  		+ "at Advocates For Elders is to empower senior citizens to acquire, demonstrate, "
			  		+ "articulate and value knowledge and skills that will support them, as life-long "
			  		+ "learners, and to participate in and contribute to their local communities.");
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Core Values')]")).isDisplayed();//Assert Office Phone field
			  String orgCoreValues = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[14]/div/div[3]/div[2]/p[1]/span")).getText();
			  //System.out.println(orgCoreValues);
			  Assert.assertEquals(orgCoreValues, "Advocates for Elders Core Values");
			  
			  driver.findElement(By.xpath("//label[contains(text(),'Organization Diversity Statement')]")).isDisplayed();//Assert Mobile phone field
			  String orgDiversityStmt = driver.findElement(By.xpath("//*[@id=\"UpdateMyProfile\"]/div[15]/div/div[3]/div[2]")).getText();
			  //System.out.println(orgDiversityStmt);
			  Assert.assertEquals(orgDiversityStmt, "We practice DEI principles each day");
			  
			  driver.findElement(By.name("Update_Profile")).isDisplayed();	//Assert Update button
			  
			  driver.findElement(By.partialLinkText("Testim")).click();		//Click on user name link
			  driver.findElement(By.xpath("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[3]/nav/ul/li/ul/li[10]/a")).click();
			  
	  }
	
	@AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }
	
}
