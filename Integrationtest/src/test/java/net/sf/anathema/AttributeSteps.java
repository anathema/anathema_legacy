package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.AttributeType;

import static net.sf.anathema.hero.traits.model.types.AttributeType.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttributeSteps {

  public static final AttributeType ANY_ATTRIBUTE_TYPE = Dexterity;
  private final CharacterHolder character;

  @Inject
  public AttributeSteps(CharacterHolder character) {
    this.character = character;
  }

  @When("^I set any of her attributes to (\\d+)$")
  public void setAnyOfHerAttributesTo(int value) throws Throwable {
    ((Trait) getAttribute(ANY_ATTRIBUTE_TYPE)).setCurrentValue(value);
  }

  @And("^I set the attribute to (\\d+)$")
  public void setTheAttributeTo(int value) throws Throwable {
    setAnyOfHerAttributesTo(value);
  }

  @Then("^she has (\\d+) dots in attribute (.*)$")
  public void she_has_dots_in_attribute(int value, String traitId) throws Throwable {
    AttributeType type = valueOf(traitId);
    assertThatAttributeHasValueOf(type, value);
  }

  @Then("^she has (\\d+) dots in the attribute$")
  public void she_has_dots_in_the_attribute(int value) throws Throwable {
    assertThatAttributeHasValueOf(ANY_ATTRIBUTE_TYPE, value);
  }

  @Then("^she has (\\d+) dots in all her attributes$")
  public void she_has_dots_in_all_her_attributes(int value) throws Throwable {
    for (AttributeType type : values()) {
      assertThatAttributeHasValueOf(type, value);
    }
  }

  @When("^she spends (\\d+) points on Mental Attributes$")
  public void she_spends_points_on_Mental_Attributes(int amount) throws Throwable {
    spendDotsOnAttributes(amount, Intelligence, Wits, Perception);
  }

  @When("^she spends (\\d+) points on Social Attributes$")
  public void she_spends_points_on_Social_Attributes(int amount) throws Throwable {
    spendDotsOnAttributes(amount, Appearance, Charisma, Manipulation);
  }

  @When("^she spends (\\d+) points on Physical Attributes$")
  public void she_spends_points_on_Physical_Attributes(int amount) throws Throwable {
    spendDotsOnAttributes(amount, Strength, Dexterity, Stamina);
  }

  private void assertThatAttributeHasValueOf(AttributeType type, int value) {
    assertThat("Attribute type " + type, getAttribute(type).getCurrentValue(), is(value));
  }

  private Trait getAttribute(AttributeType type) {
    TraitMap traitConfiguration = TraitModelFetcher.fetch(character.getCharacter());
    return traitConfiguration.getTrait(type);
  }

  private void spendDotsOnAttributes(int amount, AttributeType... attributeTypes) {
    for (; amount > 0; amount--) {
      for (AttributeType attributeType : attributeTypes) {
        Trait attribute = getAttribute(attributeType);
        if (attribute.getCreationValue() < 5) {
          attribute.setCreationValue(attribute.getCreationValue() + 1);
          break;
        }
      }
    }
  }
}
