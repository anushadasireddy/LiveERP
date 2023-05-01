package commonFunctions;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionLibrary {

	public static WebDriver driver;
	public static Properties conpro;
	public static String Actual = "";
	public static String Expected = "";

	// method to Start Browser
	public static WebDriver startBrowser() throws Throwable {
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
		//if (conpro.getProperty("Browser").equalsIgnoreCase("chrome")) {
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")){	
		driver = new ChromeDriver();
					
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			//Thread.sleep(5000);
		} else if (conpro.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
		} else {
			System.out.println("Browser is not maching");
		}
		return driver;
		
	}

	// method for launch Url
	public static void openUrl(WebDriver driver) throws Throwable {
		driver.get(conpro.getProperty("url"));
		Thread.sleep(5000);
	}

	// method is wait time to click
	public static void waitForElement(WebDriver driver, String locatorType, String locatorvalue, String WaitTime) {
		WebDriverWait mywait = new WebDriverWait(driver, Integer.parseInt(WaitTime));
		if (locatorType.equalsIgnoreCase("name")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}
	}

	// method for Textboxes
	public static void typeAction(WebDriver driver, String locatorType, String locatorvalue, String Testdata) {
		if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(Testdata);
		} else if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(Testdata);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(Testdata);
		}
	}

// method for clicking button,image, check box ,link
	public static void clickAction(WebDriver driver, String locatorType, String locatorvalue) throws Throwable {
		if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorvalue)).click();
			Thread.sleep(5000);
		} else if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.name(locatorvalue)).sendKeys(Keys.ENTER);
			Thread.sleep(5000);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.name(locatorvalue)).click();
			Thread.sleep(5000);
		}
	}

//method for validating title
	public static void validateTitle(WebDriver driver, String Expected_Title) {
		String Actual_Title = driver.getTitle();
		try {
			Assert.assertEquals(Expected_Title, Actual_Title, "Title is not matching");
		} catch (Throwable t) {
			// TODO: handle exception
			System.out.println(t.getMessage());
		}
	}

//method for closing Browser
	public static void closeBrowser(WebDriver driver) throws Throwable {
		Thread.sleep(15000);
		driver.quit();

	}

	/*public static void main(String[] args) throws Throwable {
		
		FunctionLibrary.startBrowser();
		FunctionLibrary.openUrl(driver);
		FunctionLibrary.closeBrowser(driver);
       
	}
	*/
}
