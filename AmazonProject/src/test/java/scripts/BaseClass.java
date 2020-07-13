package scripts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.ReadInputData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {

		ReadInputData rd = new ReadInputData(); 
		public String serverip = rd.getServerIp();
		public String serverport = rd.getServerPort();
		public String platformname = rd.getPlatformName();
		public String devicename = rd.getDeviceName();
		public String deviceid = rd.getDeviceId();
		public String apkpath = rd.getApkPath();
		public String apkpackage = rd.getAppPackageName();
		public String apkactivity = rd.getAppActivityName();
		public String username = rd.getUserName();
		public String password = rd.getPassword();
		public String keyword = rd.getKeyword();
		public String log4jpropath = rd.getLog4jPath();
		protected static AndroidDriver<AndroidElement> driver; 

		@BeforeSuite(alwaysRun = true) 
		public void basesetup() throws MalformedURLException {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
			cap.setCapability(MobileCapabilityType.UDID, deviceid);
			cap.setCapability(MobileCapabilityType.APP, apkpath); 
			cap.setCapability("appPackage", apkpackage);
			cap.setCapability("appActivity", apkactivity);
			cap.setCapability("noReset", false);
			String serverUrl = "http://" + serverip + ":" + serverport + "/wd/hub";
			try { 
				driver = new AndroidDriver<AndroidElement>(new URL(serverUrl), cap); 
			} catch (NullPointerException ex) {
				throw new RuntimeException("Appium driver could not be initialised for this device");
			}
		}
		
		
		
		//To take the screenshot of failed test cases
		public static void failed(String testMethodName){ 
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("./Failed_Screenshot"+"\\"+testMethodName+".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		


		@AfterSuite
		public void endTest() {
			driver.quit();
		}
	}

