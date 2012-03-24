package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class DefenseModification implements IStatsModification {

  private BaseMaterial material;

  public DefenseModification(MagicalMaterial material) {
    this.material = new BaseMaterial(material);
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material.isOrichalcumBased()) {
      return input + 1;
    }
    if (material.isMoonsilverBased()) {
      return input + 2;
    }
    return input;
  }
}