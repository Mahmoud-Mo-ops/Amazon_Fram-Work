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
	
	@FindBy(css="ul[class='a-unordered-list a-nostyle a-vertical a-spacing-none'] span[class='a-list-item']")
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
	
	public String catchError() {
		waitForElement(errorMailMessage);
		return errorMailMessage.getText();
	}	
}
