package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

import static net.sf.anathema.character.main.traits.types.AttributeType.Appearance;
import static net.sf.anathema.character.main.traits.types.AttributeType.Charisma;
import static net.sf.anathema.character.main.traits.types.AttributeType.Dexterity;
import static net.sf.anathema.character.main.traits.types.AttributeType.Intelligence;
import static net.sf.anathema.character.main.traits.types.AttributeType.Manipulation;
import static net.sf.anathema.character.main.traits.types.AttributeType.Perception;
import static net.sf.anathema.character.main.traits.types.AttributeType.Stamina;
import static net.sf.anathema.character.main.traits.types.AttributeType.Strength;
import static net.sf.anathema.character.main.traits.types.AttributeType.Wits;
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
    ((Trait) getAttribute(ANY_ATTRIBUTE_TYPE)).setCurrentValue(value);
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
