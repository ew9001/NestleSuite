import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;



@SuppressWarnings("unused")


public class CreateNewAccount {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    String path = System.getProperty("user.dir");

    
    
    // WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
    //DesiredCapabilities capability = DesiredCapabilities.firefox();

    baseUrl = "http://stage.coffee-mate.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJoinNow() throws Exception {
	  
	// User has to change the email address field for each run for script to run  
	   driver.get("http://stage.coffee-mate.com/Registration/Create-Account.aspx?email=" + timeStamp +"%40yahoo.com&stt=True");
    //driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtFirstName")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtFirstName")).sendKeys("PubmoTestFirst");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtLastName")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtLastName")).sendKeys("PubmoTestLast");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtPassword")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtPassword")).sendKeys("Password123");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtConfirmPassword")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtConfirmPassword")).sendKeys("Password123");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtAddress")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtAddress")).sendKeys("1000 BroadwAY LANE");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtAddress2")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtAddress2")).sendKeys("Suite 3V");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtCity")).sendKeys("New York");
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_ddlStates"))).selectByVisibleText("Florida");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtZipCode")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_txtZipCode")).sendKeys("10003");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_chkEmailCommunication")).click();
   //  driver.findElement(By.name("flavorTheme")).click();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRegisterUser_btnRegister")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   FileUtils.copyFile(scrFile, new File("Screenshots/" + timeStamp + "/" + "Successful-Registration-1.png"));
	   
	   
	   
	 new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_lvQuestions_ctrl0_ucSurveyQuestion_ddlAnswers"))).selectByVisibleText("0");
	 driver.findElement(By.cssSelector("option[value=\"1E4C6A78-1980-459F-BE01-049AB4CB432C\"]")).click();
	 new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_lvQuestions_ctrl1_ucSurveyQuestion_ddlAnswers"))).selectByVisibleText("0");
	 driver.findElement(By.cssSelector("option[value=\"138A6025-F98D-4A64-B36C-4A08DAB4F075\"]")).click();
	 driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnSubmit")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(4000);
		
		   File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(scrFile1, new File("Screenshots/" + timeStamp + "/" + "Successful-Registration-2.png"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}