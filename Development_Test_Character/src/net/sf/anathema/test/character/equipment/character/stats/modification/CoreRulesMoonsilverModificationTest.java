package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class CoreRulesMoonsilverModificationTest extends AbstractFirstEditionMoonsilverModificationTest {

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.CoreRules;
  }
}