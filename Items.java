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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Items {
	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
	
  @Test(priority=1)
  public void items() {
	  test=extent.createTest("Items Tab"); 
  	  
	  WebElement items= driver.findElement(By.xpath( "/html/body/div/div[1]/section[2]/div/div[3]/div/a"));
	  items.click(); 
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/NewMaster/showMasterOpeningStock";
		  String actual=driver.getCurrentUrl();
		  sa.assertEquals(actual, expected);
		  System.out.println("https://www.wahylab.com/gendemo/NewMaster/showMasterOpeningStock"+actual);
		  sa.assertAll();
		  test.pass("Clicking More Info button takes to Items Page");
	  }
	  catch(AssertionError e) {
		  System.out.println("More info button does not work");
		  test.fail("More info button does not work");
	  } 
  }
  
  @Test(priority=2)
  public void pdfDown() {
	  test=extent.createTest("Download PDF file"); 
	  
	  WebElement pdf=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[3]/span"));
	  pdf=wait.until(ExpectedConditions.visibilityOf(pdf));
	  pdf.click();
	  
	  try {
		  String download="GENTLEMAN.pdf";
		  String location="C:\\Users\\keert\\Downloads\\";
		  Thread.sleep(2000);
		  File pdffile=new File(location+"\\"+download);
		  sa.assertTrue(pdffile.exists());
		  System.out.println("file found");
		  //sa.assertAll();
		  test.pass("PDF File Found");
		  Thread.sleep(2000);
		  pdffile.delete();
	  }
	  catch(Exception e) {
		  System.out.println("No such file exist");
		  test.fail("No such file exists");
	  }
  }
  
  @Test(priority=3)
  public void excelDown() {
	  test=extent.createTest("Download Excel file"); 
	  
	  WebElement excel=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[3]/span"));
	  excel=wait.until(ExpectedConditions.visibilityOf(excel));
	  excel.click();
	  
	  try {
		  String download="GENTLEMAN.pdf";
		  String location="C:\\Users\\keert\\Downloads\\";
		  File excelfile=new File(location+"\\"+download);
		  sa.assertTrue(excelfile.exists());
		  System.out.println("Excel file found");
		  //sa.assertAll();
		  test.pass("Excel File Found");
		  Thread.sleep(2000);
		  excelfile.delete();
	  }
	  catch(Exception e) {
		  System.out.println("No such file exist");
		  test.fail("No such file exists");
	  }
  }
  
  @Test(priority=4)
  public void csvDown() {
	  test=extent.createTest("Download CSV file"); 
	  
	  WebElement csv=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[3]/span"));
	  csv=wait.until(ExpectedConditions.visibilityOf(csv));
	  csv.click();
	  
	  try {
		  String download="GENTLEMAN.csv";
		  String location="C:\\Users\\keert\\Downloads\\";
		  File csvfile=new File(location+"\\"+download);
		  sa.assertTrue(csvfile.exists());
		  System.out.println("CSV file found");
		  //sa.assertAll();
		  test.pass("CSV File Found");
		  Thread.sleep(2000);
		  csvfile.delete();
	  }
	  catch(Exception e) {
		  System.out.println("No such file exist");
		  test.fail("No such file exists");
	  }
  }
  
  @Test(priority=5)
  public void searchItems() {
	  test=extent.createTest("Search items"); 
	  
	  WebElement search=driver.findElement(By.xpath("//*[@id=\"designation_table_filter\"]/label/input"));
	  search=wait.until(ExpectedConditions.visibilityOf(search));
	  search.click();
	  //String itemName = "Employee";  
	  String itemName="Catch";
	  search.sendKeys(itemName);
	  try {
		  String expected=itemName;
		  String actual=search.getText();
		  sa.assertEquals(actual, expected);
		  //sa.assertAll();
		  test.pass("Item name found");
	  }
	  catch(AssertionError e) {
		  test.fail("Item name not found");
	  }
  }
  
  @Test(priority=6)
  public void entries() {
	  test=extent.createTest("Select entries from dropdown");
	  
			WebElement entries = driver.findElement(By.name("designation_table_length"));
			Select select=new Select(entries);
			select.selectByValue("300");
			try
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				WebElement totalEntries = driver.findElement(By.xpath("//*[@id=\"designation_table_info\"]"));
				totalEntries = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_info\"]")));
				String actual=totalEntries.getText();
				String expected="Showing 1 to 287 of 300 entries";
				sa.assertEquals(actual, expected);
				System.out.println("Dropdown value 300 is getting selected");
				test.pass("Dropdown value 300 is getting selected");
			}
			catch(Exception|AssertionError e)
			{
				test.fail("Error shows when option 300 is selected");
				System.out.println("Error when the option 300 is selected");
			}
		}
	  
  @Test(priority=7)
  public void backBtn() throws InterruptedException {
	  test=extent.createTest("Clicking back button"); 
	  
	  WebElement prev=driver.findElement(By.xpath("/html/body/div[1]/div/section[1]/ol/li[2]/a"));
	  prev=wait.until(ExpectedConditions.visibilityOf(prev));
	  prev.click();
	  Thread.sleep(1000);
	  
	  try {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		  Thread.sleep(2000);
		 WebElement exp=driver.findElement(By.xpath("//*[@id=\"designation_table_info\"]"));
		 exp=wait.until(ExpectedConditions.visibilityOf(exp));
		 
		 String expected="Showing 1 to 100 of 287 entries";
		 String actual=exp.getText();
		 sa.assertTrue(actual.contains(expected));
		 test.pass("Navigates to previous page");
	  }
	  catch(AssertionError e) {
		  test.fail("Back button does not work");
	  }
  }
  
  @Test(priority=8)
  public void homeBtn() throws InterruptedException {
	  test=extent.createTest("Clicking home button"); 
	  
	  WebElement home=driver.findElement(By.xpath("/html/body/div[1]/div/section[1]/ol/li[1]/a"));
	  home=wait.until(ExpectedConditions.visibilityOf(home));
	  home.click();
	  Thread.sleep(3000);
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/Dashboard";
		  String actual=driver.getCurrentUrl();
		  Thread.sleep(3000);
		  sa.assertEquals(actual, expected);
		  //sa.assertAll();
		  test.pass("Home button is working");
		  System.out.println("Home button is working");
	  }
	  catch(AssertionError e){
		  test.fail("Home button is not working");
	  }
	  finally {
		  WebElement items=driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div[3]/div/a"));
		  items=wait.until(ExpectedConditions.visibilityOf(items));
		  items.click();
		  
	  }
  }
  
  @Test(priority=9)
  public void sheetBtn() throws InterruptedException {
	  test=extent.createTest("Clicking sheet button"); 
	  //
	  //
	  //
	  //
	  ////*[@id="designation_table_paginate"]/ul/li[3]/a
	//*[@id="designation_table_paginate"]/ul/li[3]/a
	//*[@id="designation_table_paginate"]/ul/li[3]/a
	  
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	  Thread.sleep(2000);
	  WebElement page2=driver.findElement(By.xpath("//*[@id=\"designation_table_paginate\"]/ul/li[3]/a"));
	  page2=wait.until(ExpectedConditions.visibilityOf(page2));
	  page2.click();
	  Thread.sleep(3000);
	  
	  try {
		//  JavascriptExecutor jse = (JavascriptExecutor) driver;
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		  Thread.sleep(2000);
		 WebElement exp=driver.findElement(By.xpath("//*[@id=\"designation_table_info\"]"));
		 exp=wait.until(ExpectedConditions.visibilityOf(exp));
		  Thread.sleep(2000);

		 
		 String expected="Showing 1 to 100 of 287 entries";
		 String actual=exp.getText();
		 sa.assertTrue(actual.contains(expected));
		 test.pass("Page moved to first sheet");
	  }
	  catch(AssertionError e) {
		  test.fail("Sheet is not moving");
	  }
  }
  
  @Test(priority=10)
  public void copy() throws InterruptedException {
	  test=extent.createTest("Copying entire file"); 
	//error in webelement
	 //
	  //
	  //
	  
	  WebElement copy=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[1]/span"));
	  copy=wait.until(ExpectedConditions.visibilityOf(copy));
	  copy.click();
	  Thread.sleep(3000);
	  
	  try {
		  WebElement response=driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div/div[1]/div[1]"));
		  String expected="Copy to clipboard";
		  String actual=response.getText();
		  Thread.sleep(3000);
		  sa.assertEquals(actual, expected);
		 // sa.assertAll();
		  test.pass("All the elements gets copied");
	  }
	  catch(AssertionError e) {
		  test.fail("Data is not getting copied");
	  }
  }
  
  @Test(priority=11,enabled=false)
  public void print() throws InterruptedException {
	  test=extent.createTest("Print the file"); 
	  
	  WebElement print=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[4]/span"));
	  print=wait.until(ExpectedConditions.visibilityOf(print));
	  print.click();
	  Thread.sleep(3000);
	  
	  try {
		  WebElement response=driver.findElement(By.xpath("/html/body/embed"));
		  String expected="Save";
		  String actual=response.getText();
		  sa.assertEquals(actual, expected);
		 // sa.assertAll();
		  test.pass("Data asks to print/save");
	  }
	  catch(AssertionError e) {
		  test.fail("Data is not getting printed");
	  }
  }
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Eclipse Workspace\\RegPage\\target\\htmlReports\\items.html");
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
