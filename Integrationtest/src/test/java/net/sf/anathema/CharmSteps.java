package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import net.sf.anathema.character.generic.magic.ICharm;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharmSteps {

  private final CharacterHolder holder;

  @Inject
  public CharmSteps(CharacterHolder holder) {
    this.holder = holder;
  }

  @Then("^she can learn the Charm (.*)$")
  public void she_can_learn_the_Charm(String id) throws Throwable {
    ICharm charm = holder.getCharms().getCharmById(id);
    boolean learnable = holder.getCharms().isLearnable(charm);
    assertThat(learnable, is(true));
  }

  @Then("^she can not learn the Charm (.*)$")
  public void she_can_not_learn_the_Charm(String id) throws Throwable {
    ICharm charm = holder.getCharms().getCharmById(id);
    boolean learnable = holder.getCharms().isLearnable(charm);
    assertThat(learnable, is(false));
  }
}
