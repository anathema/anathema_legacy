package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialRangeModifier;

public class RangeModification implements StatsModification {

  private BaseMaterial material;

  public RangeModification(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    int bonus = new MaterialRangeModifier(material, type).getModifier();
    return input + bonus;
  }
}