package AmazonFrameWorkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmazonFrameWorkDesign.AbstarctComponents.AbstractComponents;

public class ShoppingCart extends AbstractComponents {
	WebDriver driver;

	public ShoppingCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#sc-buy-box-ptc-button .a-button-input")
	WebElement proceedToCheckOut;
	@FindBy(css = ".sc-list-item-content-inner .a-truncate-cut")
	List<WebElement> addedElementsToCart;

	@FindBy(id = "sc-subtotal-label-buybox")
	WebElement selectedProductText;

	public boolean verifyProduct(String ProductName) {
		boolean match = addedElementsToCart.stream()
				.anyMatch(addedElementToCart -> addedElementToCart.getText().contains(ProductName));
		return match;

	}

	public String numberOfProducts() {	
		String selectedProductText = this.selectedProductText.getText(); // Get the text content from WebElement

		// Extract the number of items
		int startIndex = selectedProductText.indexOf('(');
		int endIndex = selectedProductText.indexOf(')');

		if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
			return ""; // Handle case where '(' or ')' is not found or in incorrect order
		}

		String selectedProductNumber = selectedProductText.substring(startIndex + 1, endIndex).trim();
		return selectedProductNumber;
	}

	public CheckOut proceedToCheckout() {
		proceedToCheckOut.click();
		CheckOut checkOut = new CheckOut(driver);
		return checkOut;
		
	}

}
