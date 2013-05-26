package net.sf.anathema.exaltedengine.essence;

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

import static net.sf.anathema.exaltedengine.ExaltedEngine.ESSENCE;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EssenceSteps {

  private final CharacterHolder persona;

  @Inject
  public EssenceSteps(CharacterHolder persona) {
    this.persona = persona;
  }

  @Then("^the character has the trait '(.*?)'$")
  public void the_character_has_the_trait(String name) throws Throwable {
    QualityKey qualityKey = new QualityKey(ESSENCE, new Name(name));
    QualityClosure closure = mock(QualityClosure.class);
    persona.doFor(qualityKey, closure);
    verify(closure, atLeastOnce()).execute(isNotNull(Quality.class));
  }

  @Then("^the character has the trait '(.*?)' at (\\d+)$")
  public void the_character_has_the_trait(String name, int value) throws Throwable {
    QualityKey qualityKey = new QualityKey(ESSENCE, new Name(name));
    persona.doFor(qualityKey, new AssertValue(value));
  }


  @When("^I .*? the trait '(.*?)' to (\\d+)$")
  public void I_increase_the_trait_to(String name, int value) throws Throwable {
    QualityKey qualityKey = new QualityKey(ESSENCE, new Name(name));
    persona.execute(new SetValue(qualityKey, new NumericValue(value)));
  }
}
