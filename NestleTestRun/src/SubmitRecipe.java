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
public class SubmitRecipe {
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
    System.out.println("This script will Submit a Recipe from a user that is not logged in. Script will log the user in first");
	
    driver.get(baseUrl + "/Recipes/Default.aspx");
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRightNav_ucSubmitRecipe_txtTitle")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRightNav_ucSubmitRecipe_txtTitle")).sendKeys("Lemon Pie");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucRightNav_ucSubmitRecipe_imgbtnContinue")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.id("ctl00_ucLoginMain_txtEmail")).click();

    driver.findElement(By.id("ctl00_ucLoginMain_txtEmail")).clear();
	//Thread.sleep(2000);
    driver.findElement(By.id("ctl00_ucLoginMain_txtEmail")).sendKeys("earl.willis@publicismodem.com");
  //  driver.findElement(By.id("ctl00_ucLoginMain_txtPassword")).clear();
    driver.findElement(By.id("ctl00_ucLoginMain_txtPassword")).sendKeys("Zaq12wsx!");
    driver.findElement(By.id("ctl00_ucLoginMain_btnLogin")).click();
    driver.findElement(By.cssSelector("#ctl00_ucLoginMain_hCloseThankYou > img[alt=\"Close\"]")).click();
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_cddlRecipeType"))).selectByVisibleText("Dessert");
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctDescription")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctDescription")).sendKeys("This is my recipe for Lemon Pie");
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_cddlServings"))).selectByVisibleText("1");
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl0_txtAmount")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl0_txtAmount")).sendKeys("cup");
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl0_ddlProductType"))).selectByVisibleText("Liquid");
	Thread.sleep(3000);
    new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl0_ddlFlavorType"))).selectByVisibleText("Amaretto");
   // driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnAddCoffeeMateIngredient")).click();
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl1_txtAmount")).clear();
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl1_txtAmount")).sendKeys("Cream");
  //  new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl1_ddlProductType"))).selectByVisibleText("Powder");
  //  new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoffeeMateIngredientControl1_ddlFlavorType"))).selectByVisibleText("Parisian Almond Crme");
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctInstructions")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctInstructions")).sendKeys("This is my recipe for Lemon Pie.");

  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_OtherIngredientControl0_txtAmount")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_OtherIngredientControl0_txtAmount")).sendKeys("1 cup");
  //  driver.findElement(By.id("ctl00_ContentPlaceHolder1_OtherIngredientControl0_txtDescription")).clear();
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_OtherIngredientControl0_txtDescription")).sendKeys("Vanilla");
    driver.findElement(By.id("ctl00_ContentPlaceHolder1_CoreImageButton")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Thanks for sharing your recipe. We'll send it through our test kitchen to make sure we can make it just as tasty as you can, before we add it to Coffee-mate.com.".equals(driver.findElement(By.cssSelector("div.popinner.thxmsg > p")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	FileUtils.copyFile(scrFile, new File("Screenshots" + timeStamp + "_" + "dd.png"));

    assertEquals("Thanks for sharing your recipe. We'll send it through our test kitchen to make sure we can make it just as tasty as you can, before we add it to Coffee-mate.com.", driver.findElement(By.cssSelector("div.popinner.thxmsg > p")).getText());
    driver.findElement(By.cssSelector("img[alt=\"Close\"]")).click();
    driver.findElement(By.id("ctl00_ucProfilePanelMain_btnLogout")).click();
    driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_pnlRecipeSubmission > div.poptop > span.closer > a > img[alt=\"Close\"]")).click();
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
