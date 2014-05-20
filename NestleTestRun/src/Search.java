
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
public class Search {
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
    System.out.println("This script will test the Seach Functionality. Asssertions are made on the Search Results for the first 5 pages of results");
	
    driver.get(baseUrl + "/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	   driver.findElement(By.id("ctl00_ucThemeSearchMain_txtSiteSearch")).clear();
	    driver.findElement(By.id("ctl00_ucThemeSearchMain_txtSiteSearch")).sendKeys("coffee");
	    driver.findElement(By.id("ctl00_ucThemeSearchMain_btnSiteSearch")).click();
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Page 1 of 148".equals(driver.findElement(By.cssSelector("div.restop > p")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    assertEquals("Page 1 of 148", driver.findElement(By.cssSelector("div.restop > p")).getText());
	    driver.findElement(By.linkText("Next >>")).click();
	    System.out.println("First Page of Results loaded successfully");
	    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  	FileUtils.copyFile(scrFile, new File("Screenshots/" + timeStamp + "/" + ".png"));
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Page 2 of 148".equals(driver.findElement(By.cssSelector("div.restop > p")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    assertEquals("Page 2 of 148", driver.findElement(By.cssSelector("div.restop > p")).getText());
	    driver.findElement(By.linkText("Next >>")).click();
	    System.out.println("Second Page of Results loaded successfully");
	    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  	FileUtils.copyFile(scrFile1, new File("Screenshots/" + timeStamp + "/" + ".png"));
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Page 3 of 148".equals(driver.findElement(By.cssSelector("div.restop > p")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    assertEquals("Page 3 of 148", driver.findElement(By.cssSelector("div.restop > p")).getText());
	    driver.findElement(By.linkText("Next >>")).click();
	    System.out.println("Third Page of Results loaded successfully");
	    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  	FileUtils.copyFile(scrFile2, new File("Screenshots/" + timeStamp + "/" + ".png"));
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Page 4 of 148".equals(driver.findElement(By.cssSelector("div.restop > p")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    assertEquals("Page 4 of 148", driver.findElement(By.cssSelector("div.restop > p")).getText());
	    driver.findElement(By.linkText("Next >>")).click();
	    System.out.println("Fourth Page of Results loaded successfully");
	    File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  	FileUtils.copyFile(scrFile3, new File("Screenshots/" + timeStamp + "/" + ".png"));
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Page 5 of 148".equals(driver.findElement(By.cssSelector("div.restop > p")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    assertEquals("Page 5 of 148", driver.findElement(By.cssSelector("div.restop > p")).getText());
	    System.out.println("Fifth Page of Results loaded successfully");
	    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  	FileUtils.copyFile(scrFile5, new File("Screenshots/" + timeStamp + "/" + ".png"));

	
	
	

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
