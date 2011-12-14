package net.sf.anathema.character.equipment.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class CoreRulesOrichalcumModificationTest extends AbstractFirstEditionOrichalcumModificationTest {

  @Test
  public void addsOneToCloseCombatSpeed() throws Exception {
    assertSpeedModification(2, 1, WeaponStatsType.Melee);
    assertSpeedModification(1, 1, WeaponStatsType.Bow);
    assertSpeedModification(1, 1, WeaponStatsType.Thrown);
    assertSpeedModification(1, 1, WeaponStatsType.Thrown_BowBonuses);
    assertSpeedModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public void rateUnmodified() throws Exception {
    assertRateUnmodified();
  }

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.CoreRules;
  }
}