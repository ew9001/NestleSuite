
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;


@SuppressWarnings("unused")
public class ForgotPassword {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://stage.coffee-mate.com";
  
  }

  @Test
  public void testLogin() throws Exception {
    System.out.println("This script will request to Reset Password after user has forgotten password... Asssertions are made on the success lightbox");
	
    driver.get(baseUrl + "/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	driver.findElement(By.linkText("Sign in")).click();
    	driver.findElement(By.linkText("Forgot your password?")).click();
	    driver.findElement(By.id("ctl00_ucForgotPasswordMain_txtEmail")).clear();
	    driver.findElement(By.id("ctl00_ucForgotPasswordMain_txtEmail")).sendKeys("earl.willis@publicismodem.com");
	    driver.findElement(By.id("ctl00_ucForgotPasswordMain_btnForgotPassword")).click();
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Your request has been submitted and in a short while we'll email you a link to reset your password. Please check your inbox soon\\.[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  	FileUtils.copyFile(scrFile, new File("Screenshots/" +  "_" + "Successful-Forget-1.png"));
 
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
  }
