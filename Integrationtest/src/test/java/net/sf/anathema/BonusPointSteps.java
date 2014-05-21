package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import net.sf.anathema.hero.advance.creation.BonusPointManagement;
import net.sf.anathema.hero.points.overview.SpendingModel;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BonusPointSteps {

  private final CharacterHolder character;
  private final BonusModelFetcher bonusModel;

  @Inject
  public BonusPointSteps(CharacterHolder character) {
    this.character = character;
    this.bonusModel = new BonusModelFetcher(character);
  }

  @Then("^she has spent (\\d+) bonus points$")
  public void she_has_spent_bonus_points(int amount) throws Throwable {
    BonusPointManagement bonusPointManagement = calculateBonusPoints();
    int spentBonusPoints = bonusPointManagement.getTotalModel().getValue();
    assertThat(spentBonusPoints, is(amount));
  }

  @Then("^she has (\\d+) favored dots spent$")
  public void she_has_favored_dots_spent(int amount) throws Throwable {
    calculateBonusPoints();
    Integer dotsSpent = findBonusModel("Abilities", "FavoredDot").getValue();
    assertThat(dotsSpent, is(amount));
  }

  @Then("^she has (\\d+) ability dots spent$")
  public void she_has_ability_dots_spent(int amount) throws Throwable {
    calculateBonusPoints();
    Integer dotsSpent = findBonusModel("Abilities", "General").getValue();
    assertThat(dotsSpent, is(amount));
  }

  @Then("^she has (\\d+) favored picks spent$")
  public void she_has_favored_picks_spent(int amount) throws Throwable {
    calculateBonusPoints();
    Integer dotsSpent = findBonusModel("Abilities", "FavoredPick").getValue();
    assertThat(dotsSpent, is(amount));
  }

  @Then("^she has spent (\\d+) Charm pick$")
  public void she_has_spent_Charm_pick(int picks) throws Throwable {
    calculateBonusPoints();
    Integer dotsSpent = findBonusModel("Charms", "General").getValue();
    assertThat(dotsSpent, is(picks));
  }

  @Then("^she has all her ability dots spent$")
  public void she_has_all_her_ability_dots_spent() throws Throwable {
    SpendingModel bonusModel = findBonusModel("Abilities", "General");
    she_has_ability_dots_spent(bonusModel.getAllotment());
  }

  private SpendingModel findBonusModel(String category, String id) {
    return (SpendingModel) bonusModel.findBonusModel(category, id);
  }

  private BonusPointManagement calculateBonusPoints() {
    BonusPointManagement bonusPointManagement = new BonusPointManagement(character.getCharacter());
    bonusPointManagement.recalculate();
    return bonusPointManagement;
  }
}