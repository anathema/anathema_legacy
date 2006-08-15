package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;

public enum WeaponTag implements IWeaponTag {

  TwoHanded, Armor, BowType, ClinchEnhancer, Disarming, FlameType, LanceType, MartialArts, Natural, Overwhelming, Piercing, Reach, SingleShot, Thrown;

  public String getId() {
    return name();
  }
}