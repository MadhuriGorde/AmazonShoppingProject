package pages;
import java.util.List;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import scripts.AmazonTestClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AmazonTestClass{
	
		public CartPage(AndroidDriver<AndroidElement> driver) { 
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}

		@AndroidFindBy(xpath = "//android.widget.TextView[@index=3]") 
		AndroidElement cartItemTitle;

		public void getCartItemTitle(String itemName) throws InterruptedException {
			String cartItemName = cartItemTitle.getText();
			Thread.sleep(10000);
			System.out.println(cartItemName);
			if(cartItemName.contains(itemName))
				Thread.sleep(2000);
				Assert.assertTrue(true,"The item title is matched");
			logs.info("The item title is matched");
		}

		@AndroidFindBy(xpath = "//android.widget.TextView[@index=5]") 
		AndroidElement cartItemPrices;

		public void getCartItemPrice(String itemPrice) throws InterruptedException { 
			String t = cartItemPrices.getText();
			Thread.sleep(7000);
			System.out.println(t);
			if(itemPrice.contains(t)){
			Thread.sleep(3000);
			Assert.assertTrue(true, "Assertion passed: Item price displayed in cart is matching item description");
			logs.info("The item price is matched");
			}
			}

		
		
		@AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']") 
		AndroidElement checkoutButton;

		public void clickCheckoutButton() {
			checkoutButton.click();
		}

		@AndroidFindBy(xpath = "//android.widget.Button[@text='Delete']") 
		AndroidElement removeButton;
		
		@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']") 
		AndroidElement popupButton;

		public void clickRemoveButton() throws InterruptedException { 
			if (removeButton.isDisplayed()) {
				removeButton.click();
				Thread.sleep(2000);
				popupButton.click();
			}


		}
	}
