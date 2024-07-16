package AmazonFrameWorkDesign.pageObjects;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public void goToWebsite() {
		driver.get("https://www.amazon.com");

	}
	public void login(String email,String password) {
		SignInElement.click();
		MailElement.sendKeys(email);
		continueElement.click();
        PasswordElement.sendKeys(password);
        SubmitElement.click();

	}
	
	
}
