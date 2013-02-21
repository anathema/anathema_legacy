package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.sf.anathema.character.generic.magic.ICharm;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharmSteps {

  private final CharacterHolder character;

  @Inject
  public CharmSteps(CharacterHolder character) {
    this.character = character;
  }

  @And("^she learns the Charm (.*)$")
  public void she_learns_the_Charm(String id) throws Throwable {
    ICharm charm = character.getCharms().getCharmById(id);
    character.getCharms().getGroup(charm).toggleLearned(charm);
  }

  @Then("^she can learn the Charm (.*)$")
  public void she_can_learn_the_Charm(String id) throws Throwable {
    ICharm charm = character.getCharms().getCharmById(id);
    boolean learnable = character.getCharms().isLearnable(charm);
    assertThat(learnable, is(true));
  }

  @Then("^she can not learn the Charm (.*)$")
  public void she_can_not_learn_the_Charm(String id) throws Throwable {
    ICharm charm = character.getCharms().getCharmById(id);
    boolean learnable = character.getCharms().isLearnable(charm);
    assertThat(learnable, is(false));
  }

  @Then("^she does not know the Charm (.*)$")
  public void she_does_not_know_the_Charm(String id) throws Throwable {
    boolean learned = character.getCharms().isLearned(id);
    assertThat(learned, is(false));
  }
}