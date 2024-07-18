package Amazon.AmazonFrameworkDesign.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import AmazonFrameWorkDesign.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		Properties property = new Properties();
		// Get the current working directory
		String currentDir = System.getProperty("user.dir");
		// Construct the file path relative to the current working directory
		String filePath = currentDir + "\\src\\main\\java\\AmazonFrameWorkDesign\\resources\\GlobalData.properties";

		FileInputStream fileStream = new FileInputStream(filePath);
		property.load(fileStream);
		String browserName = property.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("weddriver.edge.driver", "edg.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public LandingPage launchApplication() throws IOException {
	  initializeDriver();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goToWebsite();
		return landingPage;
	}

}
