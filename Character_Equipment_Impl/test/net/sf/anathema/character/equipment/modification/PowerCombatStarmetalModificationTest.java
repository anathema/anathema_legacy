package net.sf.anathema.character.equipment.modification;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class PowerCombatStarmetalModificationTest extends AbstractFirstEditionStarmetalModificationTest {
  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.PowerCombat;
  }
}