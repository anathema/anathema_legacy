package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialRateModifier;

public class RateModification implements StatsModification {

  private BaseMaterial baseMaterial;

  public RateModification(BaseMaterial material) {
    this.baseMaterial = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    int bonus = new MaterialRateModifier(baseMaterial, type).getModifier();
    return Math.min(5, input + bonus);
  }
}