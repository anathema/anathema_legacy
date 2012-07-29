package net.sf.anathema.exaltedengine.attribute;

import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.exaltedengine.ExaltedEngine;
import net.sf.anathema.exaltedengine.NumericValue;
import net.sf.anathema.exaltedengine.attributes.Attribute;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static net.sf.anathema.exaltedengine.ExaltedEngine.ATTRIBUTE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressWarnings("UnusedDeclaration")
public class AttributesSteps {

  private ExaltedEngine engine = new ExaltedEngine();
  private Persona persona;

  @When("^I create an Exalted character$")
  public void I_create_an_Exalted_character() throws Throwable {
    persona = engine.createCharacter();
  }

  @Then("^the character has the Attribute (.*?)$")
  public void the_character_has_the_Attribute(String name) throws Throwable {
    QualityKey qualityKey = new QualityKey(ATTRIBUTE, new Name(name));
    QualityClosure closure = mock(QualityClosure.class);
    persona.doFor(qualityKey, closure);
    verify(closure).execute(isNotNull(Quality.class));
  }

  @Then("^each Attribute has value (\\d+)$")
  public void each_Attribute_has_value(int value) throws Throwable {
    persona.doForEach(ATTRIBUTE, new QualityClosure() {

      @Override
      public void execute(Quality quality) {
        Attribute attribute = (Attribute) quality;
        assertThat(attribute, hasValue(1));
      }
    });
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