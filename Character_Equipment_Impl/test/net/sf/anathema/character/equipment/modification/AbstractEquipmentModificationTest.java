package net.sf.anathema.character.equipment.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.AccuracyModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.DamageModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.DefenseModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.FatigueModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.HardnessModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.ReactiveBaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.MobilityPenaltyModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.RangeModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.RateModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.SoakModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.SpeedModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialAccuracyModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialDamageModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialDefenceModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialFatigueModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialHardnessModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialMobilityPenaltyModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialRangeModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialRateModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialSoakModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialSpeedModifier;
import net.sf.anathema.character.generic.health.HealthType;
import org.junit.Assert;

public abstract class AbstractEquipmentModificationTest {

  protected abstract MagicalMaterial getMagicMaterial();

  protected final void assertAccuracyModification(int expected, int original, WeaponStatsType type) {
    AccuracyModification modification = new AccuracyModification(new MaterialAccuracyModifier(getBaseMaterial(), type));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertRangeModification(int expected, int original, WeaponStatsType type) {
    RangeModification modification = new RangeModification(new MaterialRangeModifier(getBaseMaterial(), type));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertDefenseModification(int expected, int original) {
    DefenseModification modification = new DefenseModification(new MaterialDefenceModifier(getBaseMaterial()));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertSpeedModification(int expected, int original) {
    SpeedModification modification = new SpeedModification(new MaterialSpeedModifier(getBaseMaterial()));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertDamageModification(int expected, int original, WeaponStatsType type) {
    DamageModification modification = new DamageModification(new MaterialDamageModifier(getBaseMaterial(), type));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertRateModification(int expected, int original, WeaponStatsType type) {
    RateModification modification = new RateModification(new MaterialRateModifier(getBaseMaterial(), type));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertSpeedUnmodified() {
    assertSpeedModification(5, 5);
    assertSpeedModification(5, 5);
    assertSpeedModification(5, 5);
    assertSpeedModification(5, 5);
    assertSpeedModification(5, 5);
  }

  protected final void assertSoakModification(int expected, int original, HealthType type) {
    SoakModification modification = new SoakModification(new MaterialSoakModifier(getBaseMaterial(), type));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertLethalSoakUnmodified() {
    assertSoakModification(1, 1, HealthType.Lethal);
  }

  protected final void assertHardnessModification(int expected, int original) {
    HardnessModification modification = new HardnessModification(new MaterialHardnessModifier(getBaseMaterial()));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertHardnessUnmodified() {
    assertHardnessModification(1, 1);
  }

  protected final void assertMobilityPenaltyUnmodified() {
    assertMobilityPenaltyModification(-1, -1);
  }

  protected final void assertFatigueUnmodified() {
    assertFatigueModification(1, 1);
  }

  protected final void assertFatigueModification(int expected, int original) {
    FatigueModification modification = new FatigueModification(
            new MaterialFatigueModifier(getBaseMaterial(), original));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertMobilityPenaltyModification(int expected, int original) {
    MobilityPenaltyModification modification = new MobilityPenaltyModification(
            new MaterialMobilityPenaltyModifier(getBaseMaterial(), original));
    Assert.assertEquals(expected, modification.getModifiedValue(original));
  }

  protected final void assertRangeUnmodified() {
    assertRangeModification(1, 1, WeaponStatsType.Bow);
    assertRangeModification(1, 1, WeaponStatsType.Thrown);
    assertRangeModification(1, 1, WeaponStatsType.Thrown_BowBonuses);
    assertRangeModification(1, 1, WeaponStatsType.Flame);
  }

  protected final void assertRateUnmodified() {
    assertRateModification(1, 1, WeaponStatsType.Bow);
    assertRateModification(1, 1, WeaponStatsType.Thrown);
    assertRateModification(1, 1, WeaponStatsType.Thrown_BowBonuses);
    assertRateModification(1, 1, WeaponStatsType.Melee);
    assertRateModification(1, 1, WeaponStatsType.Flame);
  }

  protected final void assertDamageUnmodified() {
    assertDamageModification(1, 1, WeaponStatsType.Bow);
    assertDamageModification(1, 1, WeaponStatsType.Thrown);
    assertDamageModification(1, 1, WeaponStatsType.Thrown_BowBonuses);
    assertDamageModification(1, 1, WeaponStatsType.Melee);
    assertDamageModification(1, 1, WeaponStatsType.Flame);
  }

  protected final void assertAccuracyUnmodified() {
    assertAccuracyModification(1, 1, WeaponStatsType.Bow);
    assertAccuracyModification(1, 1, WeaponStatsType.Thrown);
    assertAccuracyModification(1, 1, WeaponStatsType.Thrown_BowBonuses);
    assertAccuracyModification(1, 1, WeaponStatsType.Melee);
    assertAccuracyModification(1, 1, WeaponStatsType.Flame);
  }

  protected final void assertDefenseUnmodified() {
    assertDefenseModification(1, 1);
    assertDefenseModification(1, 1);
    assertDefenseModification(1, 1);
    assertDefenseModification(1, 1);
    assertDefenseModification(1, 1);
  }

  private ReactiveBaseMaterial getBaseMaterial() {
    return new ReactiveBaseMaterial(getMagicMaterial());
  }
}