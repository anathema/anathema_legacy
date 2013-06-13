package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BonusPointSteps {

  private final CharacterHolder character;

  @Inject
  public BonusPointSteps(CharacterHolder character) {
    this.character = character;
  }

  @Then("^she has spent (\\d+) bonus points$")
  public void she_has_spent_bonus_points(int amount) throws Throwable {
    BonusPointManagement bonusPointManagement = calculateBonusPoints();
    int spentBonusPoints = bonusPointManagement.getTotalModel().getValue();
    assertThat(spentBonusPoints, is(amount));
  }

  @Then("^she has (\\d+) favored dots spent.$")
  public void she_has_favored_dots_spent(int amount) throws Throwable {
    BonusPointManagement bonusPointManagement = calculateBonusPoints();
    Integer dotsSpent = bonusPointManagement.getFavoredAbilityModel().getValue();
    assertThat(dotsSpent, is(amount));
  }

  @Then("^she has (\\d+) ability dots spent.$")
  public void she_has_ability_dots_spent(int amount) throws Throwable {
    BonusPointManagement bonusPointManagement = calculateBonusPoints();
    Integer dotsSpent = bonusPointManagement.getDefaultAbilityModel().getValue();
    assertThat(dotsSpent, is(amount));
  }

  @Then("^she has spent (\\d+) Charm pick$")
  public void she_has_spent_Charm_pick(int picks) throws Throwable {
    Integer value = calculateBonusPoints().getDefaultCharmModel().getValue();
    assertThat(value, is(picks));
  }

  private BonusPointManagement calculateBonusPoints() {
    BonusPointManagement bonusPointManagement = new BonusPointManagement(character.getCharacter());
    bonusPointManagement.recalculate();
    return bonusPointManagement;
  }
}