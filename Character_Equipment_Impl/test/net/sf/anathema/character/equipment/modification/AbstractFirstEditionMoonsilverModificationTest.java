package net.sf.anathema.character.equipment.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

import org.junit.Test;

public abstract class AbstractFirstEditionMoonsilverModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public final void addsTwoToCloseCombatAccuracy() throws Exception {
    assertAccuracyModification(3, 1, WeaponStatsType.Melee);
  }

  @Test
  public final void addsOneToRangedCombatAccuracy() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown_BowBonuses);
    assertAccuracyModification(2, 1, WeaponStatsType.Flame);
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
  public final void bowRangeIncreasedBy100() throws Exception {
    assertRangeModification(101, 1, WeaponStatsType.Bow);
    assertRangeModification(1, 1, WeaponStatsType.Thrown);
    assertRangeModification(101, 1, WeaponStatsType.Thrown_BowBonuses);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public final void rateUnmodified() throws Exception {
    assertRateUnmodified();
  }

  @Test
  public final void damageUnmodified() throws Exception {
    assertDamageUnmodified();
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
  public void mobilityZero() {
    assertMobilityPenaltyModification(0,7);
  }

  @Test
  public void fatigueUnmodified() {
    assertFatigueUnmodified();
  }
  
  @Override
  protected final MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Moonsilver;
  }
}