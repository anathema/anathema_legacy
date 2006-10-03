package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

import org.junit.Test;

public abstract class AbstractFirstEditionOrichalcumModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public final void addsOneToAccuracy() throws Exception {
    assertAccuracyModification(2, 1, WeaponStatsType.Bow);
    assertAccuracyModification(2, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public final void addsOneToDefense() throws Exception {
    assertDefenseModification(2, 1, WeaponStatsType.Melee);
  }

  @Test
  public final void rangedIncreasedBy50ForBows() throws Exception {
    assertRangeModification(51, 1, WeaponStatsType.Bow);
    assertRangeModification(1, 1, WeaponStatsType.Thrown);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }

  @Test
  public final void damageUnmodified() throws Exception {
    assertDamageUnmodified();
  }

  @Test
  public void soakIncreasedBy2() {
    assertSoakModification(3,1);
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
    return MagicalMaterial.Orichalcum;
  }
 }