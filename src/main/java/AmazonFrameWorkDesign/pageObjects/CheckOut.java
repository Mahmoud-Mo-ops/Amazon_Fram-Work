package AmazonFrameWorkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import AmazonFrameWorkDesign.AbstarctComponents.AbstractComponents;

public class CheckOut extends AbstractComponents {
	WebDriver driver;

	@FindBy(css = "#sc-buy-box-ptc-button .a-button-input")
	WebElement dropdownButton;

	@FindBy(css = ".a-dropdown-container .a-button-inner:nth-child(1)")
	WebElement dropdownElement;

	@FindBy(xpath = "//a[contains(text(),'Egypt')]")
	WebElement selectEgypt;

	@FindBy(css = ".a-dropdown-item")
	WebElement dropDown;

	@FindBy(xpath = "//input[@name='address-ui-widgets-enterAddressFullName']")
	WebElement name;

	@FindBy(xpath = "//input[@type='tel']")
	WebElement phoneNumber;

	@FindBy(id = "address-ui-widgets-enterAddressLine1")
	WebElement address;

	@FindBy(id = "address-ui-widgets-enter-building-name-or-number")
	WebElement buildingName;

	@FindBy(id = "address-ui-widgets-enterAddressCity")
	WebElement addressCity;

	@FindBy(id = "address-ui-widgets-enterAddressDistrictOrCounty")
	WebElement addressDistrict;

	@FindBy(id = "address-ui-widgets-enterAddressStateOrRegion")
	WebElement addressState;

	@FindBy(xpath = "//input[@autocomplete='address-ui-widgets-enterAddressDistrictOrCounty']")
	WebElement governorate;

	@FindBy(id = "address-ui-widgets-landmark")
	WebElement landmark;

	@FindBy(xpath = "//*[contains(@aria-labelledby, 'submit-button-announce')]")
	WebElement submitButton;
	@FindBy(css = ".a-declarative .a-color-link")
	String checkedOutProduct;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCountry() throws InterruptedException {
		dropdownButton.click();
		Thread.sleep(1000);
		dropdownElement.click();
		Thread.sleep(1000);
		selectEgypt.click();
		Thread.sleep(1000);

//		waitForElement(dropDown);


	}

	public void entryData(String fullname, String phoneNumberText, String addressText, String buildingNameText,
			String addressCityText, String addressDistrictText, String addressStateText, String governorateText,
			String landmarkText) {
		//Thread.sleep(1000);

		name.sendKeys(fullname);
		phoneNumber.sendKeys(phoneNumberText);
		address.sendKeys(addressText);
		buildingName.sendKeys(buildingNameText);
		addressCity.sendKeys(addressCityText);
		addressDistrict.sendKeys(addressDistrictText);
		addressState.sendKeys(addressStateText);
		governorate.sendKeys(governorateText);
		landmark.sendKeys(landmarkText);
	}

	public void submitAdresse() {
		submitButton.click();
	}

	public void validateNumberOfProduct() {
		ShoppingCart shoppingCart = new ShoppingCart(driver);
        String selectedProductNumber = shoppingCart.numberOfProducts();
		Assert.assertEquals(selectedProductNumber, checkedOutProduct, "Mismatch in the number of products.");
	}
}