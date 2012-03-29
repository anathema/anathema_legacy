package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class RateModification implements IStatsModification {

  private BaseMaterial baseMaterial;

  public RateModification(BaseMaterial material) {
    this.baseMaterial = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (baseMaterial.isOrichalcumBased() && type == WeaponStatsType.Melee) {
      return Math.min(5, input + 1);
    }
    return input;
  }
}