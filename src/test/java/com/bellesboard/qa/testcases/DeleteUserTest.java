package com.bellesboard.qa.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.HomePage;
import com.bellesboard.qa.pages.LoginPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteUserTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;	
	WebDriver wait;	
	String delUser = "testrahul5@mailcatch.com";
	public DeleteUserTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();	
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
			  	
	  @Test(priority = 1)
	  public void createUserPage() {
		  System.out.println("Login to home Page to Manage newly created user");

		  //Wait for Login
		  explicitWaitForElement("//*[@id=\"bellesBoardView\"]/div/div/div[1]/div[2]/div/a[1]");
			    
		  Assert.assertEquals(driver.getTitle(), "Home Page");
		  driver.findElement(By.partialLinkText("Welcome")).isDisplayed();
		  driver.findElement(By.partialLinkText("Welcome")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).isDisplayed();
		  driver.findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();
		  
		  String originalTitle = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[1]/div[1]/div/div[1]/h2")).getText();
		  
		  String expectedTitle = "Organization Information";
		  Assert.assertEquals(originalTitle, expectedTitle);
		  driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0_filter\"]/label/input")).sendKeys(delUser);

		  String searchedUser = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[1]")).getText();
		  
		  String expSearchedUser = "Rahul Kumar";
		  Assert.assertEquals(searchedUser, expSearchedUser);
		  driver.findElement(By.xpath("//td[8]/button/i")).click();
		  
		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert = driver.switchTo().alert();
		  System.out.println(alert.getText());
		  alert.accept();
		  		
		  Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());		  
		  System.out.println(alert1.getText());
		  alert1.accept();
		  
		  //Logout
		  driver.findElement(By.partialLinkText("Welcome")).click();		  
		  driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();		  
	  }
	  
	  @AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }
}
