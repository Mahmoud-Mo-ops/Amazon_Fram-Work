package Amazon.AmazonFrameworkDesign;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import AmazonFrameWorkDesign.pageObjects.CheckOut;
import AmazonFrameWorkDesign.pageObjects.LandingPage;
import AmazonFrameWorkDesign.pageObjects.ProductCatalogue;
import AmazonFrameWorkDesign.pageObjects.ShoppingCart;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		String ProductName = "SAMSUNG";
		String subProductName = "Galaxy Buds";

		// Setup ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Maximize the browser window and set an implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goToWebsite();
		ProductCatalogue productCatalogue=	landingPage.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		productCatalogue.searchField(ProductName);
		productCatalogue.findProductByName(subProductName);
		productCatalogue.addProductToCart(subProductName);
		ShoppingCart shoppingCar=productCatalogue.clickOnCard();
		boolean match = shoppingCar.verifyProduct(ProductName);
		Assert.assertTrue(match);
		shoppingCar.numberOfProducts();
		CheckOut checkOut =shoppingCar.proceedToCheckout();
		checkOut.selectCountry();
		checkOut.entryData("Mahmoud", "01090432848", "Kafr Asch-Schaich,Gehan Street, next to Elfath Hospital",
				"Eid , 6", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Elfahth hospital");
		checkOut.submitAdresse();
		boolean NumberOfItems = checkOut.validateNumberOfProduct();
		Assert.assertTrue(NumberOfItems);
	}
}
