package AmazonFrameWorkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import AmazonFrameWorkDesign.AbstarctComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id = "nav-link-accountList")
	WebElement SignInElement;
 
	@FindBy(id = "ap_email")
	WebElement MailElement;
 
	@FindBy(id = "continue")
	WebElement continueElement;

	@FindBy(id = "ap_password")
	WebElement PasswordElement;

	@FindBy(id="signInSubmit")
	WebElement SubmitElement;
	//h4[@class='a-alert-heading' and text()='There was a problem']

	@FindBy(xpath="//h4[@class='a-alert-heading' and text()='There was a problem']")
	WebElement errorMailMessage;

	public void goToWebsite() {
		driver.get("https://www.amazon.com");

	}
	
	@BeforeTest
	public void click() {
		SignInElement.click();
		
	}
	
	public ProductCatalogue login(String email,String password) {
		MailElement.sendKeys(email);
		continueElement.click();
        PasswordElement.sendKeys(password);
        SubmitElement.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;

	}
	
	//method for just enter invalid mail 
	public void inValidLogin(String email) {
		MailElement.sendKeys(email);
		continueElement.click();
	}
	
	public String catchError() throws InterruptedException {
		Thread.sleep(5000);
		waitForElement(errorMailMessage);
		return errorMailMessage.getText();
	}	
}
