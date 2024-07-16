package Amazon.AmazonFrameworkDesign;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

		// Navigate to Amazon
		driver.get("https://www.amazon.com");

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goToWebsite();
		landingPage.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.searchField(ProductName);
		productCatalogue.findProductByName(subProductName);
		productCatalogue.addProductToCart(subProductName);
		productCatalogue.clickOnCard();
		ShoppingCart shoppingCart = new ShoppingCart(driver);
		shoppingCart.verifyProduct(ProductName);
        shoppingCart.numberOfProducts();
        CheckOut checkOut=new CheckOut(driver);
        checkOut.selectCountry();
        checkOut.entryData("Mahmoud", "01090432848", "Kafr Asch-Schaich,Gehan Street, next to Elfath Hospital", "Eid , 6", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh","Elfahth hospital");
        checkOut.submitAdresse();
        checkOut.validateNumberOfProduct();
	}
}
