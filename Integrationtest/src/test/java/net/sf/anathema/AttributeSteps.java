package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttributeSteps {

  public static final AttributeType ANY_ATTRIBUTE_TYPE = AttributeType.Dexterity;
  private final CharacterHolder character;

  @Inject
  public AttributeSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^I set any of her attributes to (\\d+)$")
  public void setAnyOfHerAttributesTo(int value) throws Throwable {
    ((IDefaultTrait) getAttribute(ANY_ATTRIBUTE_TYPE)).setCurrentValue(value);
  }

  @And("^I set the attribute to (\\d+)$")
  public void setTheAttributeTo(int value) throws Throwable {
    setAnyOfHerAttributesTo(value);
  }

  @Then("^she has (\\d+) dots in attribute (.*)$")
  public void she_has_dots_in_attribute(int value, String traitId) throws Throwable {
    AttributeType type = AttributeType.valueOf(traitId);
    assertThatAttributeHasValueOf(type, value);
  }

  @Then("^she has (\\d+) dots in the attribute$")
  public void she_has_dots_in_the_attribute(int value) throws Throwable {
    assertThatAttributeHasValueOf(ANY_ATTRIBUTE_TYPE, value);
  }

  @Then("^she has (\\d+) dots in all her attributes$")
  public void she_has_dots_in_all_her_attributes(int value) throws Throwable {
    for (AttributeType type : AttributeType.values()) {
      assertThatAttributeHasValueOf(type, value);
    }
  }

  private void assertThatAttributeHasValueOf(AttributeType type, int value) {
    assertThat("Attribute type " + type, getAttribute(type).getCurrentValue(), is(value));
  }

  private ITrait getAttribute(AttributeType type) {
    ICoreTraitConfiguration traitConfiguration = character.getCharacter().getTraitConfiguration();
    return traitConfiguration.getTrait(type);
  }
}
