package pages;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import scripts.AmazonTestClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ItemPage extends AmazonTestClass{
	

		public ItemPage(AndroidDriver<AndroidElement> driver) {
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}	
		

		@AndroidFindBy(xpath ="//android.widget.TextView[@index='0']")
		AndroidElement itemValue;
		@AndroidFindBy(xpath ="//android.widget.TextView[@index='1']")
		AndroidElement itemValue1;
		@AndroidFindBy(xpath ="//android.widget.TextView[@index='2']")
		AndroidElement itemValue2;

		public String getItemPrice() throws Exception { 
				//String a = itemValue.getText();
				String b = itemValue1.getText();
				String c=b.concat(".");
				String e = itemValue2.getText();
				String f=c.concat(e);
				return f;
		}

		@AndroidFindBy(xpath = "//android.widget.Button[@text='Add to Cart']") // Element locator for add to cart button.
		@CacheLookup
		AndroidElement addToCartButton;
		
		
		public void addToCartAction() { 
				if (addToCartButton.isDisplayed()) {
					addToCartButton.click();
				} 
				
		}

		@AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed to checkout (1 item) $877.95']") // Element locator for open cart button
		AndroidElement cartButton;

		public void viewCart() {
				String itemDetails=cartButton.getText();
				System.out.println(itemDetails);
				cartButton.click();
				//return itemDetails;
		}
		
		

		@AndroidFindBy(xpath = "//android.widget.Button[@text='Deliver to this address C 502 garden view Parvati nagar']") // Element locator for open cart button
		AndroidElement deliverAddressButton;

		public void addressSelection() {
			deliverAddressButton.click();
		}
	}


