package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;

public enum WeaponTag implements IWeaponTag {

  TwoHanded, Armor, BowType, ClinchEnhancer, Disarming, FlameType, LanceType, MartialArts, Natural, Overwhelming,
  Piercing, Reach, SingleShot, Thrown, BowBonuses, FlatDamage, Shield0, Shield1, Shield2, Thrust;

  public String getId() {
    return name();
  }

  public static WeaponTag[] getMeleeWeaponTags() {
    return new WeaponTag[] { WeaponTag.LanceType, WeaponTag.MartialArts, WeaponTag.Natural,
    			WeaponTag.Shield0, WeaponTag.Shield1, WeaponTag.Shield2, Thrust };
  }

  public static WeaponTag[] getRangedWeaponTags() {
    return new WeaponTag[] { WeaponTag.SingleShot, WeaponTag.BowBonuses, FlatDamage };
  }

  public static WeaponTag[] getThrownWeaponTags() {
    return new WeaponTag[] { WeaponTag.BowBonuses };
  }

  public static WeaponTag[] getRangedWeaponTypeTags() {
    return new WeaponTag[] { WeaponTag.BowType, WeaponTag.Thrown, WeaponTag.FlameType };
  }
}