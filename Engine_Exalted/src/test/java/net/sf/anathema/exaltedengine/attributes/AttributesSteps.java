package net.sf.anathema.exaltedengine.attributes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.exaltedengine.ExaltedEngine;
import net.sf.anathema.exaltedengine.NumericValue;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static net.sf.anathema.exaltedengine.ExaltedEngine.ATTRIBUTE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressWarnings("UnusedDeclaration")
public class AttributesSteps {

  private ExaltedEngine engine = new ExaltedEngine();
  private Persona persona;


  @Given("^an Exalted character$")
  public void an_Exalted_character() throws Throwable {
    I_create_an_Exalted_character();
  }

  @When("^I create an Exalted character$")
  public void I_create_an_Exalted_character() throws Throwable {
    persona = engine.createCharacter();
  }

  @When("^I .*? the Attribute '(.*?)' to (\\d+)$")
  public void I_increase_the_Attribute_to(String name, int value) throws Throwable {
    QualityKey qualityKey = new QualityKey(ATTRIBUTE, new Name(name));
    persona.execute(new SetValue(qualityKey, new NumericValue(value)));
  }

  @Then("^the character has the Attribute '(.*?)'$")
  public void the_character_has_the_Attribute(String name) throws Throwable {
    QualityKey qualityKey = new QualityKey(ATTRIBUTE, new Name(name));
    QualityClosure closure = mock(QualityClosure.class);
    persona.doFor(qualityKey, closure);
    verify(closure, atLeastOnce()).execute(isNotNull(Quality.class));
  }

  @Then("^the character has the Attribute '(.*?)' at (\\d+)$")
  public void the_character_has_the_Attribute(String name, int value) throws Throwable {
    QualityKey qualityKey = new QualityKey(ATTRIBUTE, new Name(name));
    persona.doFor(qualityKey, new AssertValue(value));
  }

  @Then("^each Attribute has value (\\d+)$")
  public void each_Attribute_has_value(final int value) throws Throwable {
    persona.doForEach(ATTRIBUTE, new AssertValue(value));
  }

  private class AssertValue implements QualityClosure {

    private final int value;

    public AssertValue(int value) {
      this.value = value;
    }

    @Override
    public void execute(Quality quality) {
      Attribute attribute = (Attribute) quality;
      assertThat(attribute, hasValue(value));
    }

    private Matcher<Attribute> hasValue(final int value) {
      return new TypeSafeMatcher<Attribute>() {
        @Override
        protected boolean matchesSafely(Attribute quality) {
          return quality.hasValue(new NumericValue(value));
        }

        @Override
        public void describeTo(Description description) {
          description.appendText("a quality with value ").appendValue(value);
        }
      };
    }
  }
}