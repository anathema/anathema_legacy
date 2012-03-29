package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialFatigueModifier;

public class FatigueModification implements ArmourStatsModification {

  private final BaseMaterial magicMaterial;

  public FatigueModification(BaseMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  @Override
  public int getModifiedValue(int original) {
    int bonus = new MaterialFatigueModifier(magicMaterial, original).getModifier();
    return original - bonus;
  }
}