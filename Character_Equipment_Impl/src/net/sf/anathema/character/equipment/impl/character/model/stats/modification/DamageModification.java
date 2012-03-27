package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class DamageModification implements IStatsModification {

  private BaseMaterial material;

  public DamageModification(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material.isStarmetalBased()) {
      if (type == WeaponStatsType.Melee) {
        return input + 3;
      } else {
        return input + 2;
      }
    }
    if (material.isJadeBased() && type == WeaponStatsType.Melee) {
      return input + 1;
    }
    if (material.isOrichalcumBased() && type.isRanged()) {
      return input + 1;
    }
    return input;
  }
}