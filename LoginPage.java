package GentlemanChits.RegPage;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginPage {
	
	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
	
  @Test(priority=1)
  public void openUrl() {
	  
	  test=extent.createTest("Verify Url");
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/login/";
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
  
  @Test(priority=3)
  public void validInput() {
	  
	  test=extent.createTest("Verify login with valid credentials"); 
	  
	  WebElement name = driver.findElement(By.name("username"));
	  name=wait.until(ExpectedConditions.visibilityOf(name));
	  name.sendKeys("admin");
	 
	  
	  WebElement password = driver.findElement(By.name("password"));
	  password=wait.until(ExpectedConditions.visibilityOf(password));
	  password.sendKeys("admin@gentleman");
	  
	  //WebElement loginbutton = driver.findElement(By.tagName("button"));
	  WebElement loginbutton=driver.findElement(By.xpath("/html/body/div/div/form/button"));
	  loginbutton =wait.until(ExpectedConditions.visibilityOf(loginbutton));
	  loginbutton.click();
	  
	  try {
		 String expected="https://www.wahylab.com/gendemo/Dashboard";
		 String actual=driver.getCurrentUrl();
		 sa.assertEquals(actual,expected);
		 sa.assertAll();
		 test.pass("Login Worked");
	  }
	  catch(AssertionError e)
	  {
		  System.out.println("Login is not working");
		  test.fail("Login is not working");
	  }  
	  finally {
		WebElement signoutspace = driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/a"));
		signoutspace.click();
		WebElement signoutbutton=driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/ul/li[2]/div[2]/a"));	
		signoutbutton =wait.until(ExpectedConditions.visibilityOf(signoutbutton));
		signoutbutton.click();
		}
  }

  @Test(priority=4)
  public void invalidCase() throws IOException, InterruptedException {
	  
	  test=extent.createTest("Verify login with valid,but in different case"); 
	  
	  WebElement name = driver.findElement(By.name("username"));
	  name=wait.until(ExpectedConditions.visibilityOf(name));
	  name.sendKeys("ADMIN");
	  //WebElement name = driver.findElement(By.name("username"));
	  //WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	  //name=wait.until(ExpectedConditions.visibilityOf(name));
	  //name.sendKeys("ADMIN");
	  
	  WebElement password = driver.findElement(By.name("password"));
	  password=wait.until(ExpectedConditions.visibilityOf(password));
	  password.sendKeys("ADMIN@gentleman");
	  
	 // WebElement loginbutton = driver.findElement(By.tagName("button"));
	  WebElement loginbutton=driver.findElement(By.xpath("/html/body/div/div/form/button"));
	  loginbutton =wait.until(ExpectedConditions.visibilityOf(loginbutton));
	  loginbutton.click();
	  
	  try {
		 String expected="https://www.wahylab.com/gendemo/login/";
		 String actual=driver.getCurrentUrl();
		 Thread.sleep(3000);
		 sa.assertEquals(actual,expected);
		 sa.assertAll();
		 test.pass("Login is not working with different case");
	  }
	  catch(AssertionError e)
	  {
		  System.out.println("Login is working with different case");
		  test.fail("Login is working with different case");
		  
		  TakesScreenshot screenshot=((TakesScreenshot)driver);
		  File shot=screenshot.getScreenshotAs(OutputType.FILE);
		  Files.copy(shot,new File("C:\\Eclipse Workspace\\RegPage\\target\\screenShots\\inValidCase.png"));
	  }  
	  finally {
		  WebElement signoutspace = driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/a"));
		  signoutspace.click();
		  WebElement signoutbutton=driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/ul/li[2]/div[2]/a"));	
		  signoutbutton =wait.until(ExpectedConditions.visibilityOf(signoutbutton));
		  signoutbutton.click();
	  }
  }
  
  @Test(priority=2)
  public void invalidInput() throws InterruptedException {
	 
	  	  test=extent.createTest("Verify login with invalid input"); 
	  	  
	  	  WebElement name = driver.findElement(By.name("username"));
		  name=wait.until(ExpectedConditions.visibilityOf(name));
		  name.sendKeys("admingen");
		  Thread.sleep(3000);
		 
		  WebElement password = driver.findElement(By.name("password"));
		  password=wait.until(ExpectedConditions.visibilityOf(password));
		  password.sendKeys("admin");
		  Thread.sleep(3000);
		  
		  WebElement loginbutton = driver.findElement(By.xpath("/html/body/div/div/form/button"));
		  loginbutton =wait.until(ExpectedConditions.visibilityOf(loginbutton));
		  loginbutton.click();
		  Thread.sleep(3000);
	
	      try {
	          String expected = "https://www.wahylab.com/gendemo/login/";
	          String actual = driver.getCurrentUrl();
	          sa.assertEquals(actual, expected);
	          //System.out.println("Login is not working");
	          sa.assertAll();
	          test.pass("Login is not working with invalid input");
	          //System.out.println("Login is not working");
	      } 
	      catch (AssertionError e) {
	          System.out.printf("Login is working with invalid inputs",e.getMessage());
	          test.fail("Login is working with invalid inputs");
	      }  
	  }
  
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Eclipse Workspace\\RegPage\\target\\htmlReports\\reg.html");
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
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }
}
