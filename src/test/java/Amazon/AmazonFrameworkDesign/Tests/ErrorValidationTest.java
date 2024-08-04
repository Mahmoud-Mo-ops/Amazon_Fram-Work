package Amazon.AmazonFrameworkDesign.Tests;
import Amazon.AmazonFrameworkDesign.TestComponents.Retry;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.AmazonFrameworkDesign.TestComponents.BaseTest;
import AmazonFrameWorkDesign.pageObjects.CheckOut;
import AmazonFrameWorkDesign.pageObjects.LandingPage;
import AmazonFrameWorkDesign.pageObjects.ProductCatalogue;
import AmazonFrameWorkDesign.pageObjects.ShoppingCart;

public class ErrorValidationTest extends BaseTest {
///ENGTER WRONG MAIL 
	@Test(groups= {"ErrorValidaion"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		LandingPage landing= new LandingPage(driver);
	      landing.inValidLogin("mahmoudei@gmail.com");
		String errorMail=landing.catchError();
		Assert.assertEquals(errorMail,"There was a problem");

	}
  
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String ProductName = "SAMSUNG";
		String subProductName = "SAMSUNG";
		ProductCatalogue productCatalogue = landing.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		productCatalogue.searchField(ProductName);
		productCatalogue.findProductByName(subProductName);
		productCatalogue.addProductToCart(subProductName);
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();
		boolean match = shoppingCar.verifyProduct("i PHONE");
		Assert.assertTrue(match);
	}
	
	@Test
	public void notSelectedProduct() throws IOException, InterruptedException {
		String ProductName = "SAMSUNG";
		ProductCatalogue productCatalogue = landing.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		productCatalogue.searchField(ProductName);
		productCatalogue.findProductByName(" ");
		productCatalogue.addProductToCart(" ");
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();
		boolean match = shoppingCar.verifyProduct(ProductName);
		Assert.assertTrue(match);
	}
	
}