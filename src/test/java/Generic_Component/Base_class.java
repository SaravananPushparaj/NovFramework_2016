package Generic_Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_class {
	
	public static Process process;
	public static  AppiumDriver driver;
	
	public static void Start_Server() throws IOException, InterruptedException
	{
		String Start_server="D:\\Appium\\node.exe D:\\Appium\\node_modules\\appium\\bin\\appium.js";
		process = Runtime.getRuntime().exec(Start_server);
		
		
		if(process!=null)
		{
			
			System.out.println("Appium server is Running");
		}
		else
		{
			System.out.println("Not started the Server");
		}
		
		Thread.sleep(12000);
		
		
	}
	
	
	public static void InitializeApp() throws InterruptedException, IOException
	{
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage",Utility_Class.Reading_properties("Package_name"));
		capabilities.setCapability("appActivity",Utility_Class.Reading_properties("Activity_name"));
		
		//Appium Server details
		 driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
	}
	
	public void Explicit_wait(WebElement ele, long T1)
	{
		WebDriverWait wait= new WebDriverWait(driver, T1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		
	}
	
	
	public static void Capture_screenshot(String TC_ID, String Order) throws IOException
	{
		
		Date date= new Date();		
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");		
		File file= new File(df.format(date)+".png");
		
				
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\Nov_BB_project\\Screenshot\\"+TC_ID+"-"+Order+"-"+file));
		
		
	}
	
	
	
	
	
	public static void Stop_Server() throws InterruptedException
	{
		if(process!=null)
		{
			process.destroy();
			Thread.sleep(8000);
			System.out.println("Stopped the Appium Server");
		}
		
		
	}

}
