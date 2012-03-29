package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialHardnessModifier;

public class HardnessModification implements ArmourStatsModification {

  private BaseMaterial material;

  public HardnessModification(BaseMaterial magicMaterial) {
    this.material = magicMaterial;
  }

  @Override
  public int getModifiedValue(int original) {
    int bonus = new MaterialHardnessModifier(material).getModifier();
    return original + bonus;
  }
}