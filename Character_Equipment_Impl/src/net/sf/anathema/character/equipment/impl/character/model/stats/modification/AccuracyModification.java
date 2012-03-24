package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class AccuracyModification implements IStatsModification {

  private BaseMaterial material;

  public AccuracyModification(MagicalMaterial material) {
    this.material = new BaseMaterial(material);
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material.isSoulsteelBased()) {
      return input + 2;
    }
    if (material.isOrichalcumBased()) {
      if (type.isRanged()) {
        return input + 1;
      } else {
        return input + 2;
      }
    }
    if (material.isMoonsilverBased()) {
      if (type.isRanged()) {
        return input + 1;
      } else {
        return input + 2;
      }
    }
    if (material.isStarmetalBased()) {
      return input + 1;
    }
    return input;
  }
}