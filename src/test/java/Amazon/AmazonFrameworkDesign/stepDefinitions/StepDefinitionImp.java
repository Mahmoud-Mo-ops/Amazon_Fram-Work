package Amazon.AmazonFrameworkDesign.stepDefinitions;

import Amazon.AmazonFrameworkDesign.TestComponents.BaseTest;
import AmazonFrameWorkDesign.pageObjects.CheckOut;
import AmazonFrameWorkDesign.pageObjects.LandingPage;
import AmazonFrameWorkDesign.pageObjects.ProductCatalogue;
import AmazonFrameWorkDesign.pageObjects.ShoppingCart;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class StepDefinitionImp extends BaseTest {
    public LandingPage landing;
    public ProductCatalogue productCatalogue;
    public ShoppingCart shoppingCart;
    public CheckOut checkOut;

    @Given("I landed on the Ecommerce Page")
    public void I_landed_on_the_Ecommerce_Page() throws IOException {
        landing = launchApplication();
    }

    @Given("^logged in with Username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String email, String password) {
        productCatalogue = landing.login(email, password);
    }

    @When("^search with product (.+) in the search Field$")
    public void search_with_product(String ProductName) {
        productCatalogue.searchField(ProductName);
    }

    @When("find subProduct (.+)$")
    public void find_subProduct(String subProductName) {
        productCatalogue.findProductByName(subProductName);
    }

    @When("add subproduct (.+) to cart$")
    public void add_subproduct_to_cart(String subProductName) throws InterruptedException {
        productCatalogue.addProductToCart(subProductName);
    }

    @When("click on cartButton")
    public void click_on_cartButton() {
        shoppingCart = productCatalogue.clickOnCard();
    }

    @When("check number Of Products")
    public void check_number_Of_Products() {
        shoppingCart.numberOfProducts();
    }

    @When("proceed To Checkout")
    public void proceed_To_Checkout() {
        checkOut = shoppingCart.proceedToCheckout();
    }

    @When("select the Country")
    public void select_the_Country() throws InterruptedException {
        checkOut.selectCountry();
    }

    @When("^enter your shipping address with fullname (.+), phone number (.+), address (.+), building name (.+), city (.+), district (.+), state (.+), governorate (.+), and landmark (.+)$")
    public void enter_your_shipping_address(String fullname, String phoneNumberText, String addressText, String buildingNameText, String addressCityText, String addressDistrictText, String addressStateText, String governorateText, String landmarkText) throws InterruptedException {
        checkOut.entryData(fullname, phoneNumberText, addressText, buildingNameText, addressCityText, addressDistrictText, addressStateText, governorateText, landmarkText);
    }

    @When("submit shipping address")
    public void submit_shipping_address() {
        checkOut.submitAdresse();
    }

    @Then("check number of added orders")
    public void check_number_of_added_orders() {
        // Implement the verification of added orders
    }
}
