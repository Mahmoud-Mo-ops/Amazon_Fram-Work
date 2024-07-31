package Amazon.AmazonFrameworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Amazon.AmazonFrameworkDesign.TestComponents.BaseTest;
import AmazonFrameWorkDesign.pageObjects.CheckOut;
import AmazonFrameWorkDesign.pageObjects.ProductCatalogue;
import AmazonFrameWorkDesign.pageObjects.ShoppingCart;

public class SubmitOrderTest extends BaseTest {
	String ProductName = "SAMSUNG";
	String subProductName = "Galaxy Buds";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landing.login(input.get("email"), input.get("password"));
		productCatalogue.searchField(input.get("ProductName"));
		productCatalogue.findProductByName(input.get("subProductName"));
		productCatalogue.addProductToCart(input.get("subProductName"));
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();
		shoppingCar.numberOfProducts();
		CheckOut checkOut = shoppingCar.proceedToCheckout();
		checkOut.selectCountry();
		checkOut.entryData("Mahmoud", "01090432848", "Kafr Asch-Schaich, Gehan Street, next to Elfath Hospital",
				"Eid /6", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Kafr El Sheikh", "Elfahth hospital");
		checkOut.submitAdresse();

		boolean NumberOfItems = checkOut.validateNumberOfProduct();
		Assert.assertTrue(NumberOfItems);
	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landing.login("mahmoudeid1840@gmail.com", "Berlin@1234567");
		ShoppingCart shoppingCar = productCatalogue.clickOnCard();
		boolean match = shoppingCar.verifyProduct(ProductName);
		Assert.assertTrue(match);
	}


	@DataProvider
	public Object[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/AmazonFrameWorkDesign/data/Order.json";
		List<HashMap<String, String>> data = getJsonDataToMap(path);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
