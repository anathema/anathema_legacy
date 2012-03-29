package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialDefenceModifier;

public class DefenseModification implements StatsModification {

  private BaseMaterial material;

  public DefenseModification(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    int bonus = new MaterialDefenceModifier(material).getModifier();
    return input + bonus;
  }
}