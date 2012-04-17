package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.character.equipment.impl.character.model.stats.*;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.Identificate;

import static java.lang.Integer.MAX_VALUE;

public class GsonStatMother {
  public static RangedWeaponStats createRangedWeapon() {
    RangedWeaponStats stats = new RangedWeaponStats(new GsonCollectionFactory());
    stats.setName(new Identificate("Fire!"));
    stats.setAccuracy(0);
    stats.setDamage(0);
    stats.setDamage(0);
    stats.setDamageType(HealthType.Lethal);
    stats.setMinimumDamage(0);
    stats.setRange(400);
    stats.setRate(1);
    stats.setSpeed(5);
    return stats;
  }

  public static MeleeWeaponStats createMeleeWeapon() {
    MeleeWeaponStats stats = new MeleeWeaponStats(new GsonCollectionFactory());
    stats.setName(new Identificate("Chaaarge!"));
    stats.setAccuracy(0);
    stats.setDamage(0);
    stats.setDamage(0);
    stats.setDamageType(HealthType.Lethal);
    stats.setDefence(0);
    stats.setMinimumDamage(0);
    stats.setRate(1);
    stats.setSpeed(5);
    return stats;
  }

  public static ArtifactStats createArtifact() {
    ArtifactStats stats = new ArtifactStats();
    stats.setName(new Identificate("Zing!"));
    stats.setAllowForeignAttunement(true);
    stats.setAttuneCost(5);
    stats.setRequireAttunement(false);
    return stats;
  }

  public static ArmourStats createArmour() {
    ArmourStats stats = new ArmourStats(new GsonCollectionFactory());
    stats.setName(new Identificate("Bounce!"));
    stats.setFatigue(5);
    stats.setMobilityPenalty(-2);
    stats.setSoak(HealthType.Bashing, 5);
    stats.setSoak(HealthType.Lethal, 7);
    stats.setSoak(HealthType.Aggravated, MAX_VALUE);
    stats.setHardness(HealthType.Lethal, 7);
    return stats;
  }

  public static TraitModifyingStats createTraitModifier() {
    TraitModifyingStats stats = new TraitModifyingStats();
    stats.setName(new Identificate("Liftoff!"));
    stats.setDDVPoolMod(1);
    stats.setJoinBattleMod(2);
    stats.setJoinDebateMod(3);
    stats.setJoinWarMod(4);
    stats.setMDDVPoolMod(5);
    stats.setMeleeAccuracyMod(6);
    stats.setMeleeDamageMod(7);
    stats.setMeleeRateMod(8);
    stats.setMeleeSpeedMod(9);
    stats.setMPDVPoolMod(10);
    stats.setPDVPoolMod(11);
    stats.setRangedAccuracyMod(12);
    stats.setRangedDamageMod(13);
    stats.setRangedRateMod(14);
    stats.setRangedSpeedMod(15);
    return stats;
  }
}