package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class SecondEditionOrichalcumModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public void addsOneToAccuracy() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Melee);
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(2, 1, WeaponStatsType.Flame);
  }

  @Test
  public void addsOneToDefense() throws Exception {
    assertDefenseModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public void rateIncreasedForMeleeBy1() throws Exception {
    assertRateModification(2, 1, WeaponStatsType.Melee);
    assertRateModification(1, 1, WeaponStatsType.Bow);
    assertRateModification(1, 1, WeaponStatsType.Thrown);
    assertRateModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public void speedUnmodified() {
    assertSpeedUnmodified();
  }

  @Test
  public void damageIncreasedForRangedWeaponsBy1() throws Exception {
    assertDamageModification(1, 1, WeaponStatsType.Melee);
    assertDamageModification(2, 1, WeaponStatsType.Bow);
    assertDamageModification(2, 1, WeaponStatsType.Thrown);
    assertDamageModification(2, 1, WeaponStatsType.Flame);
  }

  @Test
  public void rangeIncreasedForBowAndThrown() throws Exception {
    assertRangeModification(51, 1, WeaponStatsType.Bow);
    assertRangeModification(11, 1, WeaponStatsType.Thrown);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public void soakIncreasedBy2() throws Exception {
    assertSoakModification(3, 1);
  }

  @Test
  public void hardnessIncreasedBy1() throws Exception {
    assertHardnessModification(2, 1);
  }

  @Test
  public void mobilityUnmodified() {
    assertMobilityPenaltyUnmodified();
  }
  
  @Test
  public void fatigueUnmodified() {
    assertFatigueUnmodified();
  }

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.SecondEdition;
  }

  @Override
  protected MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Orichalcum;
  }
}