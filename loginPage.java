package saucedemo.swaglabs;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class loginPage {

	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
	
  @Test(priority=1)
  public void openUrl() {
	  test=extent.createTest("Verifying Url");
	  
	  try {
		  String expected="https://www.saucedemo.com/";
		  String actual=driver.getCurrentUrl();
		  sa.assertEquals(actual, expected);
		  sa.assertAll(); 
		  test.pass("Valid URL");
	  }
	  catch(AssertionError e){
		  System.out.println("Invalid URL");
		  test.fail("Invalid URL");
	  }
  }
  
  @Test(priority=2)
  public void login() {
	  test=extent.createTest("Logging in with valid credentials"); 
	  
	  WebElement username = driver.findElement(By.name("user-name"));
	  username=wait.until(ExpectedConditions.visibilityOf(username));
	  username.sendKeys("standard_user");
	 
	  WebElement password = driver.findElement(By.name("password"));
	  password=wait.until(ExpectedConditions.visibilityOf(password));
	  password.sendKeys("secret_sauce");
	  
	  WebElement loginbutton=driver.findElement(By.name("login-button"));
	  loginbutton =wait.until(ExpectedConditions.visibilityOf(loginbutton));
	  loginbutton.click();
	  
	  try {
			 String expected="https://www.saucedemo.com/inventory.html";
			 String actual=driver.getCurrentUrl();
			 sa.assertEquals(actual,expected);
			 sa.assertAll();
			 test.pass("Login worked with valid credentials");
			 System.out.println("Login worked with valid credentials");
		  }
		  catch(AssertionError e)
		  {
			  System.out.println("Login is not working");
			  test.fail("Login is not working");
		  }  
  }
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReports = new ExtentSparkReporter("C:\\Eclipse Workspace\\swaglabs\\target\\htmlReports\\LoginPage");
	  extent.attachReporter(htmlReports);
  }

  @AfterClass
  public void afterClass() {
	  extent.flush();
  }

  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
	  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	  driver.get("https://www.saucedemo.com/");
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
