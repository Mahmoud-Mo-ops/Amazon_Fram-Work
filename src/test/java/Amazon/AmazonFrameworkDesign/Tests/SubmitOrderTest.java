package Amazon.AmazonFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.AmazonFrameworkDesign.TestComponents.BaseTest;
import AmazonFrameWorkDesign.pageObjects.CheckOut;
import AmazonFrameWorkDesign.pageObjects.LandingPage;
import AmazonFrameWorkDesign.pageObjects.ProductCatalogue;
import AmazonFrameWorkDesign.pageObjects.ShoppingCart;

public class SubmitOrderTest extends BaseTest {
	String ProductName = "SAMSUNG";
	String subProductName = "Galaxy Buds";
	@Test
	public void SubmitOrderTest() throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landing.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		productCatalogue.searchField(ProductName);
		productCatalogue.findProductByName(subProductName);
		productCatalogue.addProductToCart(subProductName);
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();

		shoppingCar.numberOfProducts();
		CheckOut checkOut = shoppingCar.proceedToCheckout();
		checkOut.selectCountry();
		checkOut.entryData("Mahmoud", "01090432848", "Kafr Asch-Schaich,Gehan Street, next to Elfath Hospital",
				"Eid , 6", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Elfahth hospital");
		checkOut.submitAdresse();
		//ERROR
//		boolean NumberOfItems = checkOut.validateNumberOfProduct();
//		Assert.assertTrue(NumberOfItems);
  	}
	
	
	@Test(dependsOnMethods={"SubmitOrderTest"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landing.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();
		boolean match = shoppingCar.verifyProduct(ProductName);
		Assert.assertTrue(match);	
		}
}