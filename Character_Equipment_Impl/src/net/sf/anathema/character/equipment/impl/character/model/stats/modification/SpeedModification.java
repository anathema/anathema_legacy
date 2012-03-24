package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class SpeedModification implements IStatsModification {

  private final MagicalMaterial material;
  private BaseMaterial baseMaterial;

  public SpeedModification(MagicalMaterial material) {
    this.material = material;
    this.baseMaterial = new BaseMaterial(material);
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    if (baseMaterial.isJadeBased()) {
      return Math.max(1, input - 1);
    }
    if (material == MagicalMaterial.Jade && type == WeaponStatsType.Melee) {
      return input + 3;
    }
    return input;
  }
}