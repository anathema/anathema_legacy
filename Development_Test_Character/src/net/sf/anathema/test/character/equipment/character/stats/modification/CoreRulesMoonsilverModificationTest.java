package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class CoreRulesMoonsilverModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public void addsTwoToCloseCombatAccuracy() throws Exception {
    assertAccuracyModification(3, 1, WeaponStatsType.Melee);
  }

  @Test
  public void addsOneToRangedCombatAccuracy() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
  }

  @Test
  public void defenseUnmodified() throws Exception {
    assertDefenseUnmodified();
  }

  @Test
  public void speedUnmodified() throws Exception {
    assertSpeedUnmodified();
  }

  @Test
  public void bowRangeIncreasedBy100() throws Exception {
    assertRangeModification(101, 1, WeaponStatsType.Bow);
    assertRangeModification(1, 1, WeaponStatsType.Thrown);
  }

  @Test
  public void rateUnmodified() throws Exception {
    assertRateUnmodified();
  }

  @Test
  public void damageUnmodified() throws Exception {
    assertDamageUnmodified();
  }

  @Override
  protected MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Moonsilver;
  }

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.CoreRules;
  }
}