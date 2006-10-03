package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

import org.junit.Test;

public abstract class AbstractFirstEditionStarmetalModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public final void unmodifiedAccuracy() throws Exception {
    assertAccuracyUnmodified();
  }

  @Test
  public final void defenseUnmodified() throws Exception {
    assertDefenseUnmodified();
  }

  @Test
  public final void speedUnmodified() throws Exception {
    assertSpeedUnmodified();
  }

  @Test
  public final void rangeUnmodified() throws Exception {
    assertRangeUnmodified();
  }

  @Test
  public final void rateUnmodified() throws Exception {
    assertRateUnmodified();
  }

  @Test
  public final void damageIncreasedBy2() throws Exception {
    assertDamageModification(3, 1, WeaponStatsType.Bow);
    assertDamageModification(3, 1, WeaponStatsType.Thrown);
    assertDamageModification(3, 1, WeaponStatsType.Melee);
    assertDamageModification(3, 1, WeaponStatsType.Flame);
  }

  @Test
  public void soakUnmodified() {
    assertSoakUnmodified();
  }

  @Test
  public void hardnessUnmodified() {
    assertHardnessUnmodified();
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
  protected final MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Starmetal;
  }
}