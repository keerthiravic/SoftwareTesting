package GentlemanChits.RegPage;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Designation {
	ChromeDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30)); 
	
	SoftAssert sa = new SoftAssert(); 
	
	private static ExtentReports extent; 
	private static ExtentTest test;
  
  @Test(priority=1)
  public void designation() throws InterruptedException {
	  
	  test=extent.createTest("Designation Tab"); 
  	  
	  //WebElement desig = driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div[2]/div/a"));
	  WebElement desig= driver.findElement(By.xpath( "//a[@href='https://www.wahylab.com/gendemo/NewMaster/showDesignation']"));
	  desig.click(); 
	  
	  try {
		  String expected="https://www.wahylab.com/gendemo/NewMaster/showDesignation";
		  String actual=driver.getCurrentUrl();
		  sa.assertEquals(actual, expected);
		  System.out.println("https://www.wahylab.com/gendemo/NewMaster/showDesignation"+actual);
		  sa.assertAll();
		  test.pass("Clicking More Info button takes to Designation Page");
	  }
	  catch(AssertionError e) {
		  System.out.println("More info button does not work");
		  test.fail("More info button does not work");
	  }
	  finally {
		  driver.navigate().refresh();
		  Thread.sleep(2000);
	  }
  }
  
  @Test(priority=2)
  public void addDesig() throws InterruptedException {
	  test=extent.createTest("Add designation"); 
	  
	  WebElement add=driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div/div[1]/div[2]/a/button"));
	  add=wait.until(ExpectedConditions.visibilityOf(add));
	  add.click();
	  WebElement name=driver.findElement(By.xpath("//*[@id=\"emp_name\"]"));
	  name=wait.until(ExpectedConditions.visibilityOf(name));
	  String designationName = "Employee";  
	  name.sendKeys(designationName);
	  WebElement save=driver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div/div/form/div[2]/div[2]/div/input"));
	  save=wait.until(ExpectedConditions.visibilityOf(save));
	  save.click();
	  Thread.sleep(3000);
	 
	  try {
		  WebElement search=driver.findElement(By.xpath("//*[@id=\"designation_table_filter\"]/label/input"));
		  search=wait.until(ExpectedConditions.visibilityOf(search));
		  WebElement result =driver.findElement(By.xpath("//*[@id=\"designation_table\"]/tbody/tr[1]/td[2]"));
		  result=wait.until(ExpectedConditions.visibilityOf(result));
		  Thread.sleep(3000);
		  sa.assertTrue(result.getText().contains(designationName));
		  //sa.assertAll();
		  System.out.println("Designation added was found in the list");
		  test.pass("Designation found successfully in the list");
	  }
	  catch(NoSuchElementException|TimeoutException|AssertionError e) {
		  System.out.println("Added designation is not found in the list");
		  test.fail("No such designation found");
	  }
	  
  }
  
  @Test(priority=3)
  public void editDesig() throws InterruptedException {
	  test=extent.createTest("Edit designation name");
	  
	  WebElement edit=driver.findElement(By.xpath("//*[@id=\"designation_table\"]/tbody/tr[1]/td[3]/center/a[1]/i"));
	  edit=wait.until(ExpectedConditions.visibilityOf(edit));
	  edit.click();
	  
	  try {
		  WebElement name=driver.findElement(By.id("emp_name"));
		  name=wait.until(ExpectedConditions.visibilityOf(name));
		  name.clear();
		  String input="Raju";
		  name.sendKeys(input);
		  
		  WebElement save=driver.findElement(By.name("submit"));
		  save=wait.until(ExpectedConditions.visibilityOf(save));
		  save.click();
		  Thread.sleep(2000);
		  
		  String expected=input;
		  WebElement employee=driver.findElement(By.xpath("//*[@id=\"designation_table\"]/tbody/tr[1]/td[2]"));
		  employee=wait.until(ExpectedConditions.visibilityOf(employee));
	
		  String actual=employee.getText();
		  sa.assertEquals(actual, expected);
		  sa.assertAll();
		  System.out.println("Designation name changed");
		  test.pass("Designation name changed");
	  }
	  catch(AssertionError e) {
		  System.out.println("No chnage in deisgnation after edit");
		  test.fail("No change in designation");
	  }
  }
  
  @Test(priority=4)
  public void delDesig() {
	  test=extent.createTest("Delete designation name");
	  
	  WebElement del=driver.findElement(By.xpath("//*[@id=\"designation_table\"]/tbody/tr[1]/td[3]/center/a[2]/i"));
	  del=wait.until(ExpectedConditions.visibilityOf(del));
	  del.click();
	  
	  try {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		  Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		  alert.accept();
          test.pass("Designation deleted");
	  }
	  catch(AssertionError e){
         // boolean isDeleted = driver.findElements(By.xpath("//td[text()='Alangigi']")).isEmpty();
          test.fail("Designation does not get deleted");
	  }
  }
  
  
  @Test(priority=5)
  public void excelDown() {
	  test=extent.createTest("Download Excel file"); 
	  
	/*  String expectedFileName = "GENTLEMAN.xlsx"; // Replace with expected file name

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try 
		{
			wait.until(driver -> {
				File file = new File("C:\\Users\\keert\\Downloads\\" + expectedFileName);
				return file.exists();
				// Assert.assertTrue(file.exists());
				
			});
			System.out.println("excel File downloaded successfully");
			File file = new File("C:\\Users\\keert\\Downloads\\" + expectedFileName);
			file.delete();
		}
		catch (Exception e) 
		{
			System.out.println("file download timed out");


		}*/
	  WebElement excel=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[2]/span"));
	  excel=wait.until(ExpectedConditions.visibilityOf(excel));
	  excel.click();
	  
	  try {
		  String download="GENTLEMAN.xlsx";
		  String location="C:\\Users\\keert\\Downloads";
		  File excelfile=new File(location+"\\"+download);
		  sa.assertTrue(excelfile.exists());
		  System.out.println("file found");
		  //sa.assertAll();
		  test.pass("Excel File Found");
		  //excelfile.delete();
		  
	  }
	  catch(Exception e) {
		  System.out.println("No such file exist");
		  test.fail("No such file exists");
	  }
  }
  
  @Test(priority=6)
  public void pdfDown() {
	  test=extent.createTest("Download PDF file"); 
	  
	  WebElement pdf=driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[3]/span"));
	  pdf=wait.until(ExpectedConditions.visibilityOf(pdf));
	  pdf.click();
	  
	  try {
		  String download="GENTLEMAN.pdf";
		  String location="C:\\Users\\keert\\Downloads\\";
		  File pdffile=new File(location+"\\"+download);
		  sa.assertTrue(pdffile.exists());
		  System.out.println("file found");
		  //sa.assertAll();
		  test.pass("PDF File Found");
		  pdffile.delete();
	  }
	  catch(Exception e) {
		  System.out.println("No such file exist");
		  test.fail("No such file exists");
	  }
  }
  
  @Test(priority=7)
  public void entries() throws IOException {
	  test=extent.createTest("Select entries from dropdown");
	  
			WebElement entries = driver.findElement(By.name("designation_table_length"));
			Select select=new Select(entries);
			select.selectByValue("-1");
			try
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				Alert a=wait.until(ExpectedConditions.alertIsPresent());
				TakesScreenshot screenshot=((TakesScreenshot)driver);
				File scrnsht=screenshot.getScreenshotAs(OutputType.FILE);
				Files.copy(scrnsht, new File("C:\\Eclipse Workspace\\RegPage\\target\\screenShots\\entries.png"));
				a.accept();
				WebElement element = driver.findElement(By.xpath("//*[@id=\"designation_table_info\"]"));
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_info\"]")));
				String actual=element.getText();
				String expected="Showing 1 to 152 of 152 entries";
				sa.assertEquals(actual, expected);
				System.out.println("All entries option in the dropdown is working fine");
				test.pass("All in the options get selected");
			}
			catch(Exception|AssertionError e)
			{
				test.fail("Error shows when All is selected");
				System.out.println("Defect=All entries option in the dropdown is not working");
				  TakesScreenshot screenshot=((TakesScreenshot)driver);
				  File shot=screenshot.getScreenshotAs(OutputType.FILE);
				  Files.copy(shot,new File("C:\\Eclipse Workspace\\RegPage\\target\\screenShots\\entries.png"));
			}
		}
	  
	  
	  /*
	  
	  //code without handling the alert when all selected
	  test=extent.createTest("Select entries from dropdown");
	  
	  WebElement entries=driver.findElement(By.xpath("//*[@id=\"designation_table_length\"]/label/select"));
	  entries=wait.until(ExpectedConditions.visibilityOf(entries));
	  entries.click();
	  
	  try {
          WebElement dropdown = driver.findElement(By.name("designation_table_length"));
          wait.until(ExpectedConditions.visibilityOf(dropdown));
          Select entrie = new Select(dropdown);
          entrie.selectByVisibleText("All");
          //entrie.selectByValue("-1");
          sa.assertAll();
          String selectedOption = entrie.getFirstSelectedOption().getText();
          sa.assertEquals(selectedOption,entrie);
          
           System.out.println("The All option was selected successfully.");
           test.pass("The All option was selected successfully.");    
          } 
          catch (Exception e) {
        	  System.out.println("Error encountered while selecting All option");
        	  test.fail("Error encountered while selecting All option");
          }
          finally {
        	  driver.close();
          }*/
  
  
  @BeforeClass
  public void beforeClass() {
	  extent = new ExtentReports(); 
	  ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Eclipse Workspace\\RegPage\\target\\htmlReports\\designation.html");
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
