package scripts;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;

import pages.CartPage;
import pages.HomePage;
import pages.ItemPage;
import pages.LoginPage;
import pages.MenuPage;

public class AmazonTestClass extends BaseClass {

		private HomePage hp;
		private MenuPage mp;
		private LoginPage lp;
		private ItemPage ip;
		private CartPage cp;
		public static Logger logs;
		public WebDriverWait wait;
		public ExtentReports extReporter;
		
		@BeforeTest
		public void initObjects() {
			hp = new HomePage(driver);
			mp = new MenuPage(driver);
			lp = new LoginPage(driver);
			ip = new ItemPage(driver);
			cp = new CartPage(driver);
			
			logs = Logger.getLogger(AmazonTestClass.class);
			PropertyConfigurator.configure(log4jpropath);
			wait = new WebDriverWait(driver, 30);
			ScreenOrientation ori = driver.getOrientation();
			if (ori.value() == "LANDSCAPE") {
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
		}

		@Test(priority = 1) 
		public void launchApp() {
		

			try {
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); 
				logs.info("Amazon Application launched");
				
				
			} catch (Exception e) {
				logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
				logs.fatal("Test Failed!! Remainig tests will be skipped.");
				e.printStackTrace();
				//test.fail("Amazon Application is not launched");
			}
		}

		@Test(priority = 2, dependsOnMethods = { "launchApp" }) 
		public void goToSignIn() {
			try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				mp.clickSigninText();
			} catch (Exception e) {
				logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
				logs.fatal("Test Failed!! Remainig tests will be skipped.");
				e.printStackTrace();
				Assert.fail();
			}
		}

		@Test(priority = 3, dependsOnMethods = { "goToSignIn" }) 
		public void signInToApp() throws Exception {
			try {
				//ExtentTest test = extReporter.createTest("Successfully logged in","To validated signin of app");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				logs.info("Logging in");
				Thread.sleep(5000);
				lp.enterUsername(username);
				lp.clickContinueButton();
				lp.enterPassword(password);
				lp.clickSigninButton();
				logs.info("Successfully logged in");
				//test.pass("Successfully logged in");
				
			} catch (Exception e) {
				logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
				logs.fatal("Test Failed!! Remainig tests will be skipped.");
				e.printStackTrace();
				Assert.fail();
			}
		}

		
		@SuppressWarnings({ "rawtypes", "deprecation" })
		@Test(priority = 4, dependsOnMethods = { "signInToApp" }) 
		public void searchAndSelectItemToCart() throws Exception {
			try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				hp.tapSearchBox();
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
				logs.info("Searching for the item");
				hp.enterSearchKeyword(keyword);
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
				((AndroidDriver<AndroidElement>) driver).pressKeyCode(66);
				hp.selectDropdownValue();
				Thread.sleep(5000);
				MobileElement element=driver.findElementByAndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"SAMSUNG 65-inch Class QLED Q60T Series\"));"); // **Appium UI action 'scrolling' is done here**
				String itemName=element.getText();
				System.out.println(itemName);
				Thread.sleep(2000);
				Thread.sleep(2000);
				element.click();
				logs.info("Required item is selected");
				Thread.sleep(7000);
				logs.info("Cheking item details");
				MobileElement addToCart=driver.findElementByAndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"Add to Cart\"));");
				Thread.sleep(5000);
				String itemPrice=ip.getItemPrice();
				System.out.println(itemPrice);
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				addToCart.click();
				Thread.sleep(7000);
				logs.info("Required item is added to cart");
				ip.viewCart();
				Thread.sleep(7000);
				ip.addressSelection();
				Thread.sleep(7000);
				logs.info("Checking cart details");
				/*String itemName1="SAMSUNG 65-inch Class QLED Q60T Series";
				String price1="Proceed to checkout (1 item) $877.95";*/
				cp.getCartItemTitle(itemName); 
				cp.getCartItemPrice(itemPrice);
				cp.clickCheckoutButton();
				Thread.sleep(4000);
				logs.info("Checking out");
				
			} catch (Exception e) {
				logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
				logs.fatal("Test Failed!! Remainig tests will be skipped.");
				e.printStackTrace();
				Assert.fail();
			}
		}

		@AfterTest
		public void clearCart() throws InterruptedException {
			try {
				Thread.sleep(5000);
				cp.clickRemoveButton();
				Thread.sleep(4000);
				logs.info("Removing the item from cart");
				logs.info("Post test actions: Cart cleared");
			} catch (Exception e) {
				logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
				logs.fatal("Test Failed!! Remainig tests will be skipped.");
				e.printStackTrace();
				Assert.fail();
			}
		}
	}
