package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"stepDefinition"},tags={"@All"},
        plugin={"html:target/result/cucumber-html-report","json:target/result/cucumber.json", "pretty:target/result/cucumber-pretty.txt",
                "usage:target/result/cucumber-usage.json","junit:target/result/cucumber-results.xml"},
        monochrome = true,
        strict = true
)

public class AssessmentAPIRunner {

}
