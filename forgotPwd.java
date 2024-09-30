package GentlemanChits.RegPage;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class forgotPwd {
	
	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
	
  @Test(priority=1)
  public void forgotBtn() {
	  test=extent.createTest("Forgot Pwd button"); 
  	  
	  WebElement pwd = driver.findElement(By.xpath("/html/body/div/div/a/span"));
	  pwd.click(); 
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/login/forgot_pass";
		  String actual=driver.getCurrentUrl();
		  sa.assertEquals(actual, expected);
		  sa.assertAll();
		  test.pass("Button navigates to forgot pwd page");
	  }
	  catch(AssertionError e) {
		  System.out.println("Forgot pwd button does not work");
		  test.fail("Forgot pwd button does not work");
	  } 
  }
  
  @Test(priority=2)
  public void emaild() throws InterruptedException {
	  
	  test=extent.createTest("validating email id"); 
	  try {
		  WebElement mail=driver.findElement(By.id("name"));
		  mail=wait.until(ExpectedConditions.visibilityOf(mail));
		  mail.sendKeys("admin@gentleman");
		  //mail.sendKeys("malu777@gmail.com");
		  Thread.sleep(3000);
	  
		  WebElement submitButton=driver.findElement(By.xpath("/html/body/div/div[1]/form/center/input"));
		  submitButton=wait.until(ExpectedConditions.visibilityOf(submitButton));
		  submitButton.click();
		  Thread.sleep(3000);
		  
		  WebElement response = driver.findElement(By.xpath("/html/body/div/div[2]/h2"));
		  response=wait.until(ExpectedConditions.visibilityOf(response));
		  String expectedMsg = "Please check your email!";
		  Thread.sleep(3000);
		  String actualmsg = response.getText();
		  
		  sa.assertEquals(actualmsg, expectedMsg);
		// sa.assertAll(); 
		  test.pass("Email ID validated successfully");
	    }
	  	catch (NoSuchElementException|TimeoutException|AssertionError e) {
	            test.fail("Email ID is not valid ");
	    }  
  }
  
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Eclipse Workspace\\RegPage\\target\\htmlReports\\forgotPwd.html");
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
