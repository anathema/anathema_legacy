package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class CoreRulesOrichalcumModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public void addsOneToAccuracy() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public void addsOneToDefense() throws Exception {
    assertDefenseModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public void addsOneToCloseCombatSpeed() throws Exception {
    assertSpeedModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public void rangedSpeedUnmodified() throws Exception {
    assertSpeedModification(1, 1, WeaponStatsType.Bow);
    assertSpeedModification(1, 1, WeaponStatsType.Thrown);
  }

  @Test
  public void rateUnmodified() throws Exception {
    assertRateUnmodified();
  }

  @Test
  public void rangeUnmodified() throws Exception {
    assertRangeUnmodified();
  }

  @Test
  public void damageUnmodified() throws Exception {
    assertDamageUnmodified();
  }

  @Override
  protected MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Orichalcum;
  }

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.CoreRules;
  }
}