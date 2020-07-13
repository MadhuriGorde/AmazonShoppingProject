package pages;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import scripts.AmazonTestClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.windows.PressesKeyCode;


public class HomePage extends AmazonTestClass {

		public HomePage(AndroidDriver<AndroidElement> driver) { 
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);

		}

		@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_bar_with_buttons_frame") // Element locator for search box.
		AndroidElement tapSearch;

		public void tapSearchBox() throws InterruptedException { 
			Thread.sleep(3000);	
			tapSearch.click();
			Thread.sleep(7000);
		}

		@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text") 
		AndroidElement searchTxtBox;

		@AndroidFindBy(id ="com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text") 
		AndroidElement searchDropdown;

		
		public void enterSearchKeyword(String keyword) throws InterruptedException { 

			searchTxtBox.sendKeys(keyword);
			Thread.sleep(5000);
		}

		@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text") 
		AndroidElement searchTxtBox1;
		public void selectDropdownValue() throws InterruptedException { 
			Thread.sleep(3000);
			searchTxtBox1.click();
			Thread.sleep(4000);
			searchDropdown.click();
			Thread.sleep(4000);
		}

	}

