package cucumber; // Make sure this matches your directory structure

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber", 
    glue = "Amazon.AmazonFrameworkDesign.stepDefinitions",
    monochrome = true,
    plugin = {"html:target/cucumber.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
