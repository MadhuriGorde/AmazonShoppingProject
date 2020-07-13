package pages;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import scripts.AmazonTestClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import scripts.AmazonTestClass;
public class LoginPage extends AmazonTestClass{
	
		WebDriverWait wait;
		public LoginPage(AndroidDriver<AndroidElement> driver) { 
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			wait = new WebDriverWait(driver, 60);
		}
		

		@AndroidFindBy(xpath = "//android.widget.EditText[@index='1']") 
		AndroidElement unameTxtBox;

		public void enterUsername(String uname) throws InterruptedException { 
			unameTxtBox.sendKeys(uname);
			//Thread.sleep(2000);
		}
		
		@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'continue')]") 
		AndroidElement buttonContinue;
		
		public void clickContinueButton() throws InterruptedException {
			buttonContinue.click();
			//wait.until(ExpectedConditions.elementToBeClickable(buttonContinue));
		}

		@AndroidFindBy(xpath = "//android.widget.EditText[@text='Amazon password']")
		AndroidElement pwordTxtBox;

		public void enterPassword(String pword) { 
			pwordTxtBox.sendKeys(pword);
		}

		@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'signInSubmit')]") 
		AndroidElement buttonSignin;

		public void clickSigninButton() throws InterruptedException {
			buttonSignin.click();
			Thread.sleep(8000);
		}

		
	}

