package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class SecondEditionStarmetalModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public void accuracyIncreasedBy1() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(2, 1, WeaponStatsType.Flame);
    assertAccuracyModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public void unmodifiedDefense() throws Exception {
    assertDefenseUnmodified();
  }

  @Test
  public void damageIncreasedForMeleeBy3() throws Exception {
    assertDamageModification(4, 1, WeaponStatsType.Melee);
  }

  @Test
  public void damageIncreasedForRangedBy2() throws Exception {
    assertDamageModification(3, 1, WeaponStatsType.Bow);
    assertDamageModification(3, 1, WeaponStatsType.Thrown);
    assertDamageModification(3, 1, WeaponStatsType.Flame);
  }
  
  @Test
  public void unmodifiedSpeed() {
    assertSpeedUnmodified();
  }
  
  @Test
  public void rateUnmodified() {
    assertRateUnmodified();
  }
  
  @Test
  public void unmodifiedRange() {
    assertRangeUnmodified();
  }
  
  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.SecondEdition;
  }

  @Override
  protected MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Starmetal;
  }
}