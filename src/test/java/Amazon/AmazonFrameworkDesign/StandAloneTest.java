package Amazon.AmazonFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String ProductName = "SAMSUNG";
		String subProductName = "Galaxy Buds FE";

		// Setup ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Maximize the browser window and set an implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Navigate to Amazon
		driver.get("https://www.amazon.com");

		// Perform login
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("ap_email")).sendKeys("mahmoudeid1840@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Berlin@1234567");
		driver.findElement(By.id("signInSubmit")).click();

		// Add Samsung product to the search bar
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
		Actions actions = new Actions(driver);
		actions.sendKeys(searchBar, ProductName).sendKeys(Keys.ENTER).perform();

		// Wait for search results to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".puis-card-container")));

		// Find and print the products using the correct CSS selector
		List<WebElement> products = driver.findElements(By.cssSelector(".puis-card-container"));

		// Filter and select the product
		WebElement selectedProduct = products.stream().filter(product -> product
				.findElement(By.cssSelector(".puis-padding-right-small")).getText().contains(subProductName))
				.findFirst().orElse(null);

		if (selectedProduct != null) {
//			System.out.println("THIS IS THE PRODUCT " + selectedProduct.getText());

			// Scroll to the element
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedProduct);

			// Wait for the button to be clickable
			WebElement clicked = selectedProduct.findElement(By.cssSelector("[id^='a-autoid'][id$='-announce']"));
			wait.until(ExpectedConditions.elementToBeClickable(clicked));

			// Use JavaScript to click the button
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", clicked);

			System.out.println("THIS IS CLICKED ELE button " + clicked.getText());
		} else {
			System.out.println("Product not found.");
		}

		// Adding a delay to observe the action
		Thread.sleep(5000);

		// Verify the added item
		driver.findElement(By.id("nav-cart")).click();
		List<WebElement> addedElementsToCart = driver
				.findElements(By.cssSelector(".sc-list-item-content-inner .a-truncate-cut"));

		boolean match = addedElementsToCart.stream()
				.anyMatch(addedElementToCart -> addedElementToCart.getText().contains(ProductName));
		Assert.assertTrue(match, "The product was not added to the cart.");

		// Specify number of the selected products
		String selectedProductText = driver.findElement(By.id("sc-subtotal-label-buybox")).getText();
		// Extract the number of items
		int startIndex = selectedProductText.indexOf('(');
		int endIndex = selectedProductText.indexOf(')');
		String selectedProductNumber = selectedProductText.substring(startIndex + 1, endIndex).trim();
		System.out.println("Number of selected products: " + selectedProductNumber);

		// Proceed to checkout
		driver.findElement(By.cssSelector("#sc-buy-box-ptc-button .a-button-input")).click();

		// Handle the custom dropdown
		WebElement dropdownElement = driver
				.findElement(By.cssSelector(".a-dropdown-container .a-button-inner:nth-child(1)"));
		dropdownElement.click();
		// Wait for the options to be visible and click the desired option
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".a-dropdown-item")));
		driver.findElement(By.xpath("//a[contains(text(),'Egypt')]")).click();
		Thread.sleep(1000);
		// Enter shipping details
		driver.findElement(By.xpath("//input[@name='address-ui-widgets-enterAddressFullName']"))
				.sendKeys("Mahmoud Eid Goma");
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("1090432948");
		driver.findElement(By.id("address-ui-widgets-enterAddressLine1"))
				.sendKeys("Kafr Asch-Schaich,Gehan Street, next to Elfath Hospital");
		driver.findElement(By.id("address-ui-widgets-enter-building-name-or-number")).sendKeys("Eid , 6");
		driver.findElement(By.id("address-ui-widgets-enterAddressCity")).sendKeys("Kafr El Sheikh");
		driver.findElement(By.id("address-ui-widgets-enterAddressDistrictOrCounty")).sendKeys("Kafr El Sheikh");
		driver.findElement(By.id("address-ui-widgets-enterAddressStateOrRegion")).sendKeys("Kafr El Sheikh");
		driver.findElement(By.xpath("//input[@autocomplete='address-ui-widgets-enterAddressDistrictOrCounty']"))
				.sendKeys("Kafr El Sheikh");
		driver.findElement(By.id("address-ui-widgets-landmark")).sendKeys("Elfahth hospital");

		WebElement submitButton = driver
				.findElement(By.xpath("//*[contains(@aria-labelledby, 'submit-button-announce')]"));
		submitButton.click();

		// Extract the number of elements in the checkout page
		String checkedOutProduct = driver.findElement(By.cssSelector(".a-declarative .a-color-link")).getText();
		System.out.println("Checked out product: " + checkedOutProduct);

		// Validate that the number of products in the checkout page equals the number
		// of products in the cart
		Assert.assertEquals(selectedProductNumber, checkedOutProduct, "Mismatch in the number of products.");	
		
	}
}
