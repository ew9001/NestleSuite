
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
public class LoginExistingUser {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://stage.coffee-mate.com";
  
  }

  @Test
  public void testLogin() throws Exception {
    System.out.println("This script will log an existing user in...and then log the user out. Asssertions are made on the Welcome Page and the Home Page");
	
    driver.get(baseUrl + "/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.linkText("Sign in")).click();
    //driver.findElement(By.name("ctl00$ucJoinNowMain$txtSignInEmail")).clear();
    driver.findElement(By.name("ctl00$ucJoinNowMain$txtSignInEmail")).sendKeys("earl.willis@publicismodem.com");
    driver.findElement(By.name("ctl00$ucJoinNowMain$txtSignInPassword")).clear();
    driver.findElement(By.name("ctl00$ucJoinNowMain$txtSignInPassword")).sendKeys("Zaq12wsx!");
    System.out.println("Login: earl.willis@publicismodem.com | Password:zaq12wsx");
    driver.findElement(By.id("ctl00_ucJoinNowMain_btnSignIn")).click();
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Thank you for logging in\\.[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  	FileUtils.copyFile(scrFile, new File("Y://Screenshots/CoffeeMate/" + timeStamp + "/" + "Successful-Login-1.png"));

    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Thank you for logging in\\.[\\s\\S]*$"));
    driver.findElement(By.id("ctl00_ucJoinNowMain_Img2")).click();
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*My Profile[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(2000);
    }
 
    // Warning: assertTextPresent may require manual changes
 	
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*My Profile[\\s\\S]*$"));
    driver.findElement(By.id("ctl00_ucProfilePanelMain_btnLogout")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Already registered[\\s\\S] Sign in[\\s\\S]*$")) break; } catch (Exception e) {}

    }
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  	FileUtils.copyFile(scrFile1, new File("Y://Screenshots/CoffeeMate/" + timeStamp + "/" + "Successful-Logout-1.png"));
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Already registered[\\s\\S] Sign in[\\s\\S]*$"));
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

