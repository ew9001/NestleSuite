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
public class SavingsCalculator {
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
    System.out.println("This script will navigate to the Saving Calculator Page and verify the Search is pulling up results for: 5 cups, Regular - $1.50");
	
    driver.get(baseUrl + "/My-Cafe/Default.aspx");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucSavingsCalc_ddlNumDrinks"))).selectByVisibleText("5 cups");
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucSavingsCalc_ddlDrinkTypes"))).selectByVisibleText("Regular coffee w/ cream - $1.50");
    driver.findElement(By.cssSelector("option[value=\"b893b016-b453-4851-93be-2e81e53de780\"]")).click();
    driver.findElement(By.cssSelector("img[alt=\"Calculate Now\"]")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Enjoy your coffeehouse drink at home and save money!".equals(driver.findElement(By.cssSelector("div.calcres > h4")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    assertEquals("Enjoy your coffeehouse drink at home and save money!", driver.findElement(By.cssSelector("div.calcres > h4")).getText());
    
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  	FileUtils.copyFile(scrFile, new File("Screenshots/" + timeStamp + "/" + "sav.png"));
 
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