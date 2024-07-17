package AmazonFrameWorkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmazonFrameWorkDesign.AbstarctComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchFiled;

	@FindBy(css = ".puis-padding-right-small")
	List<WebElement> selectedProducts;

	@FindBy(css = ".puis-card-container")
	List<WebElement> cardsContainer;
	
	@FindBy(id="nav-cart")
	WebElement goToCard;
	
	By clickButton = By.cssSelector("[id^='a-autoid'][id$='-announce']");
     
	public void searchField(String ProductName) {
		waitForElement(searchFiled);
		Actions actions = new Actions(driver);
		actions.sendKeys(searchFiled, ProductName).sendKeys(Keys.ENTER).perform();
	}

	public List<WebElement> getProducts() {
		waitForElement(cardsContainer);
		System.out.println(selectedProducts);
		return selectedProducts;
	}

	public WebElement findProductByName(String subProductName) {
		WebElement selectedProduct = cardsContainer.stream().filter(product -> product
				.findElement(By.cssSelector(".puis-padding-right-small")).getText().contains(subProductName))
				.findFirst().orElse(null);
		return selectedProduct;
	}

	public void addProductToCart(String subProductName) throws InterruptedException {
		WebElement selectedProduct = findProductByName(subProductName);
		if (selectedProduct != null) {
			// Scroll to the element
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedProduct);

			// Find the button within the selected product
			WebElement button = selectedProduct.findElement(clickButton);

			waitForClickableBy(clickButton);
			// Use JavaScript to click the button
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
//			  
			System.out.println("THIS IS CLICKED ELE button " + button.getText());
		} else {
			System.out.println("Product not found.");
		}
		Thread.sleep(5000);
	}
	
	//reusable and common usage //would be better to add to abstract
	public ShoppingCart clickOnCard() {
		goToCard.click();
		ShoppingCart shoppingCart = new ShoppingCart(driver);
		return shoppingCart;

	}
}
