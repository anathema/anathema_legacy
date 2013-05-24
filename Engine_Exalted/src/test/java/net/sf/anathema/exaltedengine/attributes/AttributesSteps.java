package net.sf.anathema.exaltedengine.attributes;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import net.sf.anathema.exaltedengine.numericquality.SetValue;
import net.sf.anathema.exaltedengine.support.AssertValue;
import net.sf.anathema.exaltedengine.support.CharacterHolder;

import static net.sf.anathema.exaltedengine.ExaltedEngine.ATTRIBUTE;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressWarnings("UnusedDeclaration")
public class AttributesSteps {

  private final CharacterHolder persona;

  @Inject
  public AttributesSteps(CharacterHolder persona) {
    this.persona = persona;
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

}