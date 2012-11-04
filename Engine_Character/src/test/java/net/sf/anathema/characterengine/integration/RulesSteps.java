package net.sf.anathema.characterengine.integration;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.support.NumericQualityMinimumValueRule;

public class RulesSteps {
  private final Engine engine;
  private final EngineCharacter character;

  @Inject
  public RulesSteps(Engine engine, EngineCharacter character) throws Exception {
    this.engine = engine;
    this.character = character;
  }

  @Given("^a rule that an (.*) must at least be (\\d+)$")
  public void a_rule_that_an_Attribute_must_at_least_be(final String type, final int minimum) throws Throwable {
    character.execute(new Command() {
      @Override
      public void execute(Qualities qualities) {
        qualities.defineRule(new Type(type), new NumericQualityMinimumValueRule(minimum));
      }
    });
  }
}