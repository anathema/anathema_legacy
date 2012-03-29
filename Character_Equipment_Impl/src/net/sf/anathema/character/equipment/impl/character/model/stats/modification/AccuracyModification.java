package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialAccuracyModifier;

public class AccuracyModification implements StatsModification {

  private BaseMaterial material;

  public AccuracyModification(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    int bonus = new MaterialAccuracyModifier(material, type).getModifier();
    return input + bonus;
  }
}