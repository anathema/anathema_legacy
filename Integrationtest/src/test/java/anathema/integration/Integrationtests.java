package anathema.integration;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(tags = {"@Integration"}, features="classpath:net/sf/anathema",  glue = "classpath:net/sf/anathema")
public class Integrationtests {
}
