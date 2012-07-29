package net.sf.anathema.exaltedengine;

import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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
    QualityKey qualityKey = new QualityKey(new Type("Attribute"), new Name(name));
    QualityClosure closure = mock(QualityClosure.class);
    persona.doFor(qualityKey, closure);
    verify(closure).execute(isNotNull(Quality.class));
  }

  @Then("^each Attribute has value (\\d+)$")
  public void each_Attribute_has_value(int value) throws Throwable {
    persona.doForEach(new Type("Attribute"), new QualityClosure() {

      @Override
      public void execute(Quality quality) {
        NumericQuality numericQuality = (NumericQuality) quality;
        assertThat(numericQuality, hasValue(1));
      }
    });
  }

  private Matcher<NumericQuality> hasValue(final int value) {
      return new TypeSafeMatcher<NumericQuality>() {
        @Override
        protected boolean matchesSafely(NumericQuality quality) {
          return quality.hasValue(new NumericValue(value));
        }

        @Override
        public void describeTo(Description description) {
          description.appendText("a quality with value ").appendValue(value);
        }
      };
  }
}