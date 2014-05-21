package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.points.overview.SpendingModel;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbilitySteps {

  public static final int ASSUMED_THRESHOLD_FOR_BONUSPOINTS = 3;
  private final CharacterHolder character;

  private final BonusModelFetcher bonusModel;

  @Inject
  public AbilitySteps(CharacterHolder character) {
    this.character = character;
    this.bonusModel = new BonusModelFetcher(character);
  }
  @When("^I favor her (.*)$")
  public void favor_her(String abilityName) {
    Trait ability = character.getTraitConfiguration().getTrait(AbilityType.valueOf(abilityName));
    ability.getFavorization().setFavored(true);
  }

  @Then("^she has (\\d+) dots in ability (.*)$")
  public void she_has_dots_in_Ability(int amount, String abilityName) throws Throwable {
    Trait ability = character.getTraitConfiguration().getTrait(AbilityType.valueOf(abilityName));
    assertThat(ability.getCurrentValue(), is(amount));
  }

  @And("^she spends all her general Ability dots$")
  public void she_spends_all_her_Ability_dots() throws Throwable {
    she_exceeds_her_Ability_allotment_by_dot(0);
  }

  @When("^she exceeds her Ability allotment by (\\d+) dot$")
  public void she_exceeds_her_Ability_allotment_by_dot(int overspending) throws Throwable {
    int pointsToSpend = determinePointsToSpend(overspending, "Abilities", "General");
    spendPoints(pointsToSpend);
  }

  private int determinePointsToSpend(int overspending, String category, String id) {
    SpendingModel model = (SpendingModel) bonusModel.findBonusModel(category, id);
    int freeAbilityPoints = model.getAllotment();
    return freeAbilityPoints + overspending;
  }

  private void spendPoints(int pointsToSpend) {
    for (;pointsToSpend>0; pointsToSpend--){
      spendAPoint();
    }
  }

  private void spendAPoint() {
    Trait[] traits = character.getTraitConfiguration().getAll();
    for (Trait trait : traits) {
      boolean isAbility = trait.getType() instanceof AbilityType;
      boolean hasNotYetReachedThreshold = trait.getCreationValue() < ASSUMED_THRESHOLD_FOR_BONUSPOINTS;
      boolean isNotFavored = !trait.getFavorization().isFavored();
      if (isAbility && hasNotYetReachedThreshold && isNotFavored){
        increaseTraitValueByOne(trait);
        break;
      }
    }
  }

  private int determinePointsToSpend(int overspending) {
    SpendingModel model = (SpendingModel) bonusModel.findBonusModel("Abilities", "General");
    int freeAbilityPoints = model.getAllotment();
    return freeAbilityPoints+overspending;
  }

  private void increaseTraitValueByOne(Trait trait) {
    trait.setCreationValue(trait.getCreationValue() + 1);
  }
}
