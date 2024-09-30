package GentlemanChits.RegPage;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.NoSuchElementException;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Header {
	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
  @Test(priority=1)
  public void dashboard() {
	  test=extent.createTest("Dashboard");
	  WebElement dash=driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div[2]/div/a"));
	  dash=wait.until(ExpectedConditions.visibilityOf(dash));
	  dash.click();
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/NewMaster/showDesignation";
		  String actual=driver.getCurrentUrl();
		  sa.assertEquals(actual, expected);
		  sa.assertAll();
		  test.pass("Button navigates to designation field");
	  }
	  catch(AssertionError e) {
		  System.out.println("More info button doesn't work");
		  test.fail("More info button of designation item doesn't work");
	  }
  }
  
  @Test(priority=2)
  public void userDetails() {
	  test=extent.createTest("User Details");
	  WebElement user=driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/a/span[1]"));
	  user=wait.until(ExpectedConditions.visibilityOf(user));
	  user.click();
	  
	  try {
		  WebElement details=driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/ul"));
		  details=wait.until(ExpectedConditions.visibilityOf(details));
		  sa.assertEquals(true, true);
		  sa.assertAll();
		  test.pass("User details are visible");
	  }
	  catch(NoSuchElementException|TimeoutException e) {
		  test.fail("User details are not visible");
	  }
  }
  
  @Test(priority=3)
  public void signOut() {
	  test=extent.createTest("Sign Out");
	  //WebElement sig/html/body/div/header/nav/div/ul/li[1]/a
	  WebElement signout=driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/ul/li[2]/div[2]/a"));
	  signout=wait.until(ExpectedConditions.visibilityOf(signout));
	  signout.click();
	  
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/login/";
		  String actual=driver.getCurrentUrl();
		  sa.assertEquals(actual, expected);
		  sa.assertAll();
		  test.pass("Sign out button works");
	  }
	  catch(AssertionError e) {
		  test.fail("Sign out button does not work");
	  }
  }
  
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Eclipse Workspace\\RegPage\\target\\htmlReports\\header.html");
	  extent.attachReporter(htmlReporter);
  }

  @AfterClass
  public void afterClass() {
	  extent.flush();
  }

  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
	  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	  driver.get("https://www.wahylab.com/gendemo/login/");
	  driver.manage().window().maximize();
	  
	  WebElement name = driver.findElement(By.name("username"));
	  name=wait.until(ExpectedConditions.visibilityOf(name));
	  name.sendKeys("admin");
	
	  WebElement password = driver.findElement(By.name("password"));
	  password=wait.until(ExpectedConditions.visibilityOf(password));
	  password.sendKeys("admin@gentleman");
	  
	  WebElement loginbutton=driver.findElement(By.xpath("/html/body/div/div/form/button"));
	  loginbutton =wait.until(ExpectedConditions.visibilityOf(loginbutton));
	  loginbutton.click();
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
