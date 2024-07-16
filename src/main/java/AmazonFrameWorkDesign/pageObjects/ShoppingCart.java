package AmazonFrameWorkDesign.pageObjects;

import java.util.List;

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


	@FindBy(css = "sc-list-item-content-inner .a-truncate-cut")
	List<WebElement> addedElementsToCart;

	@FindBy(id="sc-subtotal-label-buybox")
	WebElement selectedProductText;
	public boolean verifyProduct(String ProductName) {
		boolean match = addedElementsToCart.stream()
				.anyMatch(addedElementToCart -> addedElementToCart.getText().contains(ProductName));
		return match;

	}
	
	public String numberOfProducts() {
		//In the context of your ShoppingCart class, when you write this.selectedProductText.getText(), you are explicitly referring to the selectedProductText field (which is a WebElement defined in your class) and calling its getText() method. This ensures that you are accessing the WebElement instance variable of the class and fetching its visible text content.
	    String selectedProductText =this.selectedProductText.getText(); // Get the text content from WebElement

	    // Extract the number of items
	    int startIndex = selectedProductText.indexOf('(');
	    int endIndex = selectedProductText.indexOf(')');
	    
	    if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
	        return ""; // Handle case where '(' or ')' is not found or in incorrect order
	    }

	    String selectedProductNumber = selectedProductText.substring(startIndex + 1, endIndex).trim();
	    return selectedProductNumber;
			}

}
