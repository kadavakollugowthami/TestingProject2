package allurereports;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Listeners({AllureListener.class})
// public class tests extends BaseClass
public class tests 
{
   
//	public WebDriver driver;
//	@BeforeClass
//	public void setup()
//	{
//		BaseClass bs=new BaseClass();
//		bs.initialize_driver();
//		driver.get("https://demo.nopcommerce.com/");
//		driver.manage().window().maximize();
//	}
    WebDriver driver;
	@BeforeClass
	public void setup()
	{
	    	WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		    driver.get("https://demo.nopcommerce.com/");
		    driver.manage().window().maximize();
	}
	@Test(priority=1,description="Verify logo")
	@Description("verify logo in home page")
	@Epic("EP001")
	@Feature("Feature1:Logo")
	@Story("Story: Logo Presence")
	@Step("Verify logo presence")
	@Severity(SeverityLevel.MINOR)
	public void logoPresence()
	{ 
		boolean disstatus=driver.findElement(By.xpath("//div[@class='header-logo']//a//img")).isDisplayed();
		Assert.assertEquals(disstatus, true);
		
	}
	@Test(priority=2)
	@Description("verify login page")
	@Epic("EP001")
	@Feature("Feature2:Login")
	@Story("Story: Valid Login")
	@Step("Verify login")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest()
	{ 
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys("pavanoltraining@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("@Test@123");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store12");
		
	}
	@Test(priority=3)
	@Description("Register")
	@Epic("EP001")
	@Feature("Feature3:Registration")
	@Story("Story: User Registration")
	@Severity(SeverityLevel.NORMAL)
	public void registrationTest()
	{
		throw new SkipException("Skipping this Test");
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
