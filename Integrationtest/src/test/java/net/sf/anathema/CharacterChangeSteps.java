package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.main.experience.model.ExperienceModelFetcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharacterChangeSteps {

  private final CharacterHolder character;

  @Inject
  public CharacterChangeSteps(CharacterHolder character) {
    this.character = character;
  }

  @Given("^her current Essence is (\\d+)$")
  public void herCurrentEssenceIs(int value) throws Throwable {
    I_set_her_trait_to("Essence", value);
  }

  @Given("^she is experienced")
  @When("^she goes experienced")
  public void setToExperienced() {
    ExperienceModelFetcher.fetch(character.getCharacter()).setExperienced(true);
  }

  @When("^I set her Caste to (.*)$")
  public void I_set_her_Caste(String casteName) throws Throwable {
    ICasteCollection casteCollection = character.getCharacterTemplate().getCasteCollection();
    ICasteType caste = casteCollection.getById(casteName);
    character.getCharacterConcept().getCaste().setType(caste);
  }

  @When("^I set her (.*) to (\\d+)$")
  public void I_set_her_trait_to(String traitId, int value) throws Throwable {
    ITraitType type = new TraitTypeUtils().getTraitTypeById(traitId);
    IModifiableBasicTrait trait = (IModifiableBasicTrait) character.getTraitConfiguration().getTrait(type);
    if (ExperienceModelFetcher.fetch(character.getCharacter()).isExperienced()) {
      trait.setExperiencedValue(value);
    } else {
      trait.setCreationValue(value);
    }
  }

  @Then("^she has (\\d+) dots in ability (.*)$")
  public void she_has_dots_in_Ability(int amount, String abilityName) throws Throwable {
    ITrait ability = character.getTraitConfiguration().getTrait(AbilityType.valueOf(abilityName));
    assertThat(ability.getCurrentValue(), is(amount));
  }
}