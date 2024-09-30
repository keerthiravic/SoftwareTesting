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

public class priceInCart {
	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
	
  @Test(priority=1)
  public void checkInCart() {
	  test=extent.createTest("Checking price in cart");
	  
	  WebElement cart=driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
	  cart =wait.until(ExpectedConditions.visibilityOf(cart));
	  cart.click();
	  
	  try {
		  String expectedPrice="$29.99";
		  WebElement actualPrice=driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
		  sa.assertEquals(expectedPrice, actualPrice);
		  test.pass("Price in the cart and in the product page is same");
		  System.out.println("Price in the cart and in the product page is same");
	  }
	  catch(AssertionError e) {
		  test.fail("Price given in the product page and cart is not the same");
		  System.out.println("Price given in the product page and cart is not the same");
	  }
  }
  
  @Test(priority=2)
  public void removeProduct() {
	  test=extent.createTest("Removing product from cart");
	  
	  WebElement remove=driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
	  remove=wait.until(ExpectedConditions.visibilityOf(remove));
	  remove.click();
	  
	 /* try {
		  WebElement expected=driver.findElement(By.xpath("[@id=\"cart_contents_container\"]/div/div[2]"));
		  WebElement actual=driver.findElement(By.xpath("[@id=\"cart_contents_container\"]/div/div[2]"));
		  
		//*[@id="cart_contents_container"]/div/div[2]
		//*[@id="cart_contents_container"]/div/div[1]/div[3]/div[2]
	  }
	  */
	  
  }
  
  @Test(priority=3)
  public void signOut() {
	  test=extent.createTest("Logging out from the page");
	  
	  WebElement menu=driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
	  menu=wait.until(ExpectedConditions.visibilityOf(menu));
	  menu.click();
	  
	  WebElement logout=driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
	  logout=wait.until(ExpectedConditions.visibilityOf(logout));
	  logout.click();
  }
  
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReports = new ExtentSparkReporter("C:\\Eclipse Workspace\\swaglabs\\target\\htmlReports\\PriceInCart");
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
	  
	  WebElement username = driver.findElement(By.name("user-name"));
	  username=wait.until(ExpectedConditions.visibilityOf(username));
	  username.sendKeys("standard_user");
	 
	  WebElement password = driver.findElement(By.name("password"));
	  password=wait.until(ExpectedConditions.visibilityOf(password));
	  password.sendKeys("secret_sauce");
	  
	  WebElement loginbutton=driver.findElement(By.name("login-button"));
	  loginbutton =wait.until(ExpectedConditions.visibilityOf(loginbutton));
	  loginbutton.click();
	  
	  WebElement product=driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
	  product =wait.until(ExpectedConditions.visibilityOf(product));
	  product.click();
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
