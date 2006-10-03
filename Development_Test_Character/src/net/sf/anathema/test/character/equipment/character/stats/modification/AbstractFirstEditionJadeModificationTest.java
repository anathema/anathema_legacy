package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

import org.junit.Test;

public abstract class AbstractFirstEditionJadeModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public final void unmodifiedAccuracy() throws Exception {
    assertAccuracyUnmodified();
  }

  @Test
  public final void defenseUnmodified() throws Exception {
    assertDefenseUnmodified();
  }

  @Test
  public final void speedForMeleeIncreasedBy3() throws Exception {
    assertSpeedModification(4, 1, WeaponStatsType.Melee);
    assertSpeedModification(1, 1, WeaponStatsType.Bow);
    assertSpeedModification(1, 1, WeaponStatsType.Thrown);
    assertSpeedModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public final void bowRangeIncreasedBy50() throws Exception {
    assertRangeModification(51, 1, WeaponStatsType.Bow);
    assertRangeModification(1, 1, WeaponStatsType.Thrown);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public final void rateForRangedCombatIncreasedBy1() throws Exception {
    assertRateModification(2, 1, WeaponStatsType.Bow);
    assertRateModification(2, 1, WeaponStatsType.Thrown);
    assertRateModification(2, 1, WeaponStatsType.Flame);
    assertRateModification(1, 1, WeaponStatsType.Melee);
  }

  @Test
  public final void damageUnmodified() throws Exception {
    assertDamageUnmodified();
  }

  @Override
  protected final MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Jade;
  }
}