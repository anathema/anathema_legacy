package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class RateModification implements IStatsModification {

  private BaseMaterial baseMaterial;

  public RateModification(MagicalMaterial material) {
    this.baseMaterial = new BaseMaterial(material);
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (baseMaterial.isOrichalcumBased() && type == WeaponStatsType.Melee) {
      return input + 1;
    }
    return input;
  }
}