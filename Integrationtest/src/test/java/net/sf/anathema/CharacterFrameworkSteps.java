package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharacterFrameworkSteps {
  private final CharacterHolder character;

  @Inject
  public CharacterFrameworkSteps(CharacterHolder character) {
    this.character = character;
  }

  @Then("^the character needs to be saved$")
  public void the_character_needs_to_be_saved() throws Throwable {
    assertThat(character.getCharacter().isDirty(), is(true));
  }
}