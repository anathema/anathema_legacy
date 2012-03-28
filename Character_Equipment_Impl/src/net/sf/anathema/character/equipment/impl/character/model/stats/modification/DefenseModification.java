package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class DefenseModification implements IStatsModification {

  private BaseMaterial material;

  public DefenseModification(BaseMaterial material) {
    this.material = material;
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