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
import org.openqa.selenium.support.ui.Select;

@SuppressWarnings("unused")
public class ContactForm {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://stage.coffee-mate.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContact() throws Exception {
    driver.get(baseUrl + "/Contact-Us.aspx");
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlReason"))).selectByVisibleText("General Inquiry or Question");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtFirstName")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtFirstName")).sendKeys("PubmoTestFirst");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtLastName")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtLastName")).sendKeys("PubmoTestLast");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtEmail")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtEmail")).sendKeys("xxxxx@yahoo.com");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtAddress1")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtAddress1")).sendKeys("75 W. 10th Street");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtAddress2")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtAddress2")).sendKeys("Apt. 3B");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtCity")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtCity")).sendKeys("New York");
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlStates"))).selectByVisibleText("New York");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtZip")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtZip")).sendKeys("10003");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtHomePhone1")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtHomePhone1")).sendKeys("212");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtHomePhone2")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtHomePhone2")).sendKeys("222");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtHomePhone3")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtHomePhone3")).sendKeys("2222");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtWorkPhone1")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtWorkPhone1")).sendKeys("212");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtWorkPhone2")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtWorkPhone2")).sendKeys("456");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtWorkPhone3")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtWorkPhone3")).sendKeys("7890");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtComments")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtComments")).sendKeys("Please do not contact this test account.");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_imgbtnSubmit")).click();
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Thanks for taking the time to get in touch with us[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Thanks for taking the time to get in touch with us[\\s\\S]*$"));
    // ERROR: Caught exception [unknown command []]
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	FileUtils.copyFile(scrFile, new File("Screenshots/" + "Successful-Contact-Submit-1.png"));
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