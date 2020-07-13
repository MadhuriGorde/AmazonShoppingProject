package pages;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import scripts.AmazonTestClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage extends AmazonTestClass{
	
		public MenuPage(AndroidDriver<AndroidElement> driver) { 
		      PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
		
		@AndroidFindBy(xpath = "//android.widget.Button[@text='Already a customer? Sign in']") 
		AndroidElement signinButton;

		public void clickSigninText() throws InterruptedException { 
				signinButton.click();
				
		}
	}


