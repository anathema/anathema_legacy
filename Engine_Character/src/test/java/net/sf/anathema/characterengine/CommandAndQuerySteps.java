package net.sf.anathema.characterengine;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("UnusedDeclaration")
public class CommandAndQuerySteps {

  private Persona persona;

  @Given("^a character$")
  public void a_character() throws Throwable {
    this.persona = new DefaultPersona(new DefaultQualities());
  }

  @When("^I add the (.*?) '(.*?)' to the character$")
  public void I_add_the_quality_to_the_character(String type, String name) throws Throwable {
    Type qualityType = new Type(type);
    Name qualityName = new Name(name);
    persona.execute(new AddQuality(qualityType, qualityName));
  }

  @When("^the character does not know a quality$")
  public void the_character_does_not_know_a_quality() throws Throwable {
    //nothing to do
  }

  @Then("^the character can operate with the (.*?) '(.*?)'$")
  public void the_character_has_the_quality(String type, String name) throws Throwable {
    assertCommandExecuted(type, name, true);
  }

  @Then("^the character ignores commands for the quality$")
  public void the_character_ignores_commands_for_the_quality() throws Throwable {
    assertCommandExecuted("unknownType", "unknownName", false);
  }

  private void assertCommandExecuted(String type, String name, boolean expectedValue) {
    final boolean[] wasCalled = new boolean[]{false};
    persona.doFor(new Type(type), new Name(name), new Closure() {
      @Override
      public void execute(Quality quality) {
        wasCalled[0] = true;
      }
    });
    assertThat(wasCalled[0], is(expectedValue));
  }
}