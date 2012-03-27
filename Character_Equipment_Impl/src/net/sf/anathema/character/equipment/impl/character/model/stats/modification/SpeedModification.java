package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class SpeedModification implements IStatsModification {

  private BaseMaterial baseMaterial;

  public SpeedModification(BaseMaterial material) {
    this.baseMaterial = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (baseMaterial.isJadeBased()) {
      return Math.max(1, input - 1);
    }
    return input;
  }
}