package Amazon.AmazonFrameworkDesign.Tests;
import Amazon.AmazonFrameworkDesign.TestComponents.Retry;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.AmazonFrameworkDesign.TestComponents.BaseTest;
import AmazonFrameWorkDesign.pageObjects.CheckOut;
import AmazonFrameWorkDesign.pageObjects.ProductCatalogue;
import AmazonFrameWorkDesign.pageObjects.ShoppingCart;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorValidaion"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landing.login("mahmoudei@gmail.com","Berlin@1234567");

		String errorMail=landing.catchError();
		Assert.assertEquals(errorMail,"We cannot find an account with that email address");
	}
	  
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String ProductName = "SAMSUNG";
		String subProductName = "I Phone";
		ProductCatalogue productCatalogue = landing.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		productCatalogue.searchField(ProductName);
		productCatalogue.findProductByName(subProductName);
		productCatalogue.addProductToCart(subProductName);
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();
		boolean match = shoppingCar.verifyProduct("i PHONE");
		Assert.assertTrue(match);
	}
	
	
}