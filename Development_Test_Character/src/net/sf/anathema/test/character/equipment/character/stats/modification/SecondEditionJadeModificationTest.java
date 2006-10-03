package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class SecondEditionJadeModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public void unmodifiedAccuracy() throws Exception {
    assertAccuracyUnmodified();
  }

  @Test
  public void unmodifiedDefense() throws Exception {
    assertDefenseUnmodified();
  }

  @Test
  public void damageIncreasedForMeleeBy1() throws Exception {
    assertDamageModification(2, 1, WeaponStatsType.Melee);
    assertDamageModification(1, 1, WeaponStatsType.Bow);
    assertDamageModification(1, 1, WeaponStatsType.Thrown);
    assertDamageModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public void speedReducedBy1() {
    assertSpeedModification(1, 2, WeaponStatsType.Bow);
    assertSpeedModification(1, 2, WeaponStatsType.Thrown);
    assertSpeedModification(1, 2, WeaponStatsType.Melee);
    assertSpeedModification(1, 2, WeaponStatsType.Flame);
  }
  
  @Test
  public void speedReductionLimitedTo1() {
    assertSpeedModification(1, 1, WeaponStatsType.Bow);
  }
  
  @Test
  public void rateUnmodified() {
    assertRateUnmodified();
  }
  
  @Test
  public void rangeIncreasedForBowAndThrown() {
    assertRangeModification(51, 1, WeaponStatsType.Bow);
    assertRangeModification(11, 1, WeaponStatsType.Thrown);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }
  
  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.SecondEdition;
  }

  @Override
  protected MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Jade;
  }
}