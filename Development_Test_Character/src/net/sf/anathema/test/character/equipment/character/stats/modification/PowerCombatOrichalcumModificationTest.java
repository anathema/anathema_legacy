package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class PowerCombatOrichalcumModificationTest extends AbstractFirstEditionOrichalcumModificationTest {

  @Test
  public void speedUnmodified() throws Exception {
    assertSpeedUnmodified();
  }

  @Test
  public void addsOneToCloseCombatRate() throws Exception {
    assertRateModification(2, 1, WeaponStatsType.Melee);
    assertRateModification(1, 1, WeaponStatsType.Bow);
    assertRateModification(1, 1, WeaponStatsType.Thrown);
    assertRateModification(1, 1, WeaponStatsType.Flame);
  }

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.PowerCombat;
  }
}