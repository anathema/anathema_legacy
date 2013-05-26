package net.sf.anathema.exaltedengine;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.exaltedengine.support.CharacterHolder;

public class PersonaSteps {

  private final ExaltedEngine engine;
  private final CharacterHolder holder;

  @Inject
  public PersonaSteps(ExaltedEngine engine, CharacterHolder holder){
    this.engine = engine;
    this.holder = holder;
  }

  @Given("^an Exalted character$")
  public void an_Exalted_character() throws Throwable {
    I_create_an_Exalted_character();
  }

  @When("^I create an Exalted character$")
  public void I_create_an_Exalted_character() throws Throwable {
    Persona persona = engine.createCharacter();
    holder.hold(persona);
  }
}