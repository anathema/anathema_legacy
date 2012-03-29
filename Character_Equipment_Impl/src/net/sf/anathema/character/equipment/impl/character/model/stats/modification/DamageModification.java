package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialDamageModifier;

public class DamageModification implements StatsModification {

  private BaseMaterial material;

  public DamageModification(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int getModifiedValue(int input, WeaponStatsType type) {
    int bonus = new MaterialDamageModifier(material,type).getModifier();
    return input + bonus;
  }

}