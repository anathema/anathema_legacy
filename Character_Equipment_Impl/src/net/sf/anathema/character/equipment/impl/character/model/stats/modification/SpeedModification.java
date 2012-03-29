package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialSpeedModifier;

public class SpeedModification implements StatsModification {

  private BaseMaterial baseMaterial;

  public SpeedModification(BaseMaterial material) {
    this.baseMaterial = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    int bonus = new MaterialSpeedModifier(baseMaterial).getModifier();
    return Math.max(3, input - bonus);
  }
}