package net.sf.anathema.characterengine.integration;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.characterengine.command.AddQuality;
import net.sf.anathema.characterengine.engine.DefaultEngine;
import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.support.DummyQualityFactory;
import net.sf.anathema.characterengine.support.IncreaseBy;
import net.sf.anathema.characterengine.support.NumericQuality;
import net.sf.anathema.characterengine.support.NumericQualityFactory;
import net.sf.anathema.characterengine.support.NumericValue;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@SuppressWarnings("UnusedDeclaration")
public class CommandAndQuerySteps {

  private Engine engine = new DefaultEngine();
  private Persona persona;
  private QualityListener registeredListener;

  @Before
  public void addDummyQuality() {
    engine.setFactory(new Type("Attribute"), new DummyQualityFactory());
  }

  @Given("^a character$")
  public void a_character() throws Throwable {
    this.persona = engine.createCharacter();
  }

  @Given("^a rule that an (.*?) starts with value (\\d+)$")
  public void a_rule_that_type_starts_with_value(String type, int startValue) throws Throwable {
    engine.setFactory(new Type(type), new NumericQualityFactory(startValue));
  }


  @Given("^a registered listener for the (.*?) '(.*?)'$")
  public void a_registered_listener_for(String type, String name) throws Throwable {
    I_register_a_listener_on_the_character(type, name);
  }

  @When("^I add the (.*?) '(.*?)' to the character$")
  public void I_add_the_quality_to_the_character(String type, String name) throws Throwable {
    QualityKey qualityKey = QualityKey.ForTypeAndName(type, name);
    persona.execute(new AddQuality(qualityKey));
  }

  @When("^the character does not know a quality$")
  public void the_character_does_not_know_a_quality() throws Throwable {
    //nothing to do
  }

  @When("^I increase the value of the (.*?) '(.*?)' by (\\d+)$")
  public void I_increase_the_value_of_the_Attribute_Toughness_by(String type, String name,
                                                                 int modification) throws Throwable {
    QualityKey qualityKey = QualityKey.ForTypeAndName(type, name);
    persona.execute(new IncreaseBy(qualityKey, new NumericValue(modification)));
  }

  @When("^I register a listener for the (.*?) '(.*?)' on the character$")
  public void I_register_a_listener_on_the_character(String type, String name) throws Throwable {
    QualityKey qualityKey = QualityKey.ForTypeAndName(type, name);
    registeredListener = mock(QualityListener.class);
    persona.observe(qualityKey, registeredListener);
  }

  @When("^I remove the listener for the (.*?) '(.*?)'$")
  public void I_remove_the_listener(String type, String name) throws Throwable {
    QualityKey qualityKey = QualityKey.ForTypeAndName(type, name);
    persona.stopObservation(qualityKey, registeredListener);
  }

  @Then("^I am not notified about the new Attribute 'Toughness'$")
  public void I_am_not_notified_about_the_new_Attribute_Toughness() throws Throwable {
    verifyZeroInteractions(registeredListener);
  }

  @Then("^I am notified about the new Attribute 'Toughness'$")
  public void I_am_notified_about_the_new_Attribute_Toughness() throws Throwable {
    verify(registeredListener).eventOccurred();
  }

  @Then("^the character can operate with the (.*?) '(.*?)'$")
  public void the_character_has_the_quality(String type, String name) throws Throwable {
    assertCommandExecuted(type, name, true);
  }

  @Then("^the character ignores commands for the quality$")
  public void the_character_ignores_commands_for_the_quality() throws Throwable {
    assertCommandExecuted("unknownType", "unknownName", false);
  }

  @Then("^(.*?) '(.*?)' has the value (\\d+)$")
  public void the_quality_has_the_value(String type, String name, final int value) throws Throwable {
    persona.doFor(QualityKey.ForTypeAndName(type, name), new QualityClosure() {
      @Override
      public void execute(Quality quality) {
        NumericQuality numericQuality = (NumericQuality) quality;
        assertThat(numericQuality, hasValue(new NumericValue(value)));
      }
    });
  }

  private void assertCommandExecuted(String type, String name, boolean expectedValue) {
    final boolean[] wasCalled = new boolean[]{false};
    QualityKey qualityKey = QualityKey.ForTypeAndName(type, name);
    persona.doFor(qualityKey, new QualityClosure() {
      @Override
      public void execute(Quality quality) {
        wasCalled[0] = true;
      }
    });
    assertThat(wasCalled[0], is(expectedValue));
  }

  private Matcher<NumericQuality> hasValue(final NumericValue value) {
    return new TypeSafeMatcher<NumericQuality>() {
      @Override
      protected boolean matchesSafely(NumericQuality quality) {
        return quality.hasValue(value);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("a quality with value ").appendValue(value);
      }
    };
  }
}