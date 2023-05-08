package com.bellesboard.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bellesboard.qa.base.TestBase;
import com.bellesboard.qa.pages.LoginPage;


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
	
	@Test
	public void LoginPageTitleTest() {
		String title = loginPage.verifyTitle();
		Assert.assertEquals(title, "Login");
	}
	
	@Test
	public void LoginTest() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		 try {
			    Thread.sleep(5000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
	}
	
	@AfterMethod
	  public void closeBrowser() {
		  tearDown();
	  }
}
