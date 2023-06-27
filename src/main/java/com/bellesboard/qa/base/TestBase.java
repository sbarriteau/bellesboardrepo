package com.bellesboard.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bellesboard.qa.util.TestUtil;

public class TestBase {
	private static final long randomNum = 0;
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
			
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/bellesboard/qa/config/config.properties");
			prop.load(ip);
			
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}		
		}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/java/drivers/chromedriver.exe");
			driver = new ChromeDriver(co);			
		} else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/java/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
	
	//driver.manage().
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);	
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("url"));			
	}
		
	public static int randomNumber() 
	    {
	        for(int i=0; i<5; i++)
	        {
	            int randomNum = ThreadLocalRandom.current().nextInt(20, 30 + 1);
	            System.out.println(randomNum);
	            
	        }
			return (int) randomNum; 	        
	        
	    }
	
	public static String RandomStringPwd() 
    {		      
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?1234567890";
		
		String pwd = RandomStringUtils.random( 10, characters );
		System.out.println( pwd );		
		return pwd; 	                
    }
	
	public static String generateEmailwithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		String randomEmail = "testuser"+timestamp+"@mailcatch.com";
		return randomEmail;	
	}
	
	public static void tearDown() {
		driver.quit();
	}
	
	//Pass xpath value of any element for wait as parameter
	public static void explicitWaitForElement(String element) {
	// explicit wait condition
    WebDriverWait w = new WebDriverWait(driver,30);
    // presenceOfElementLocated condition
    w.until(ExpectedConditions.presenceOfElementLocated (By.xpath(element)));
	}
	
	public static void pause(Integer milliseconds){
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean isAlertPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			return true;			
		}catch(NoAlertPresentException e) {
			return false;
		}
	}
}

