package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import org.junit.Test;

public class SecondEditionOrichalcumModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public void addsOneToAccuracy() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(4, 3, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(4, 3, WeaponStatsType.Thrown);
    assertAccuracyModification(2, 1, WeaponStatsType.Melee);
    assertAccuracyModification(4, 3, WeaponStatsType.Melee);
  }

  @Test
  public void addsOneToDefense() throws Exception {
    assertDefenseModification(2, 1, WeaponStatsType.Melee);
    assertDefenseModification(4, 3, WeaponStatsType.Melee);
  }

  @Override
  protected MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Orichalcum;
  }

  @Test
  public void rangeIncreasedForBowAndThrown() throws Exception {
    assertRangeModification(51, 1, WeaponStatsType.Bow);
    assertRangeModification(11, 1, WeaponStatsType.Thrown);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }

  @Override
  protected IExaltedRuleSet getRuleSet() {
    return ExaltedRuleSet.SecondEdition;
  }
}