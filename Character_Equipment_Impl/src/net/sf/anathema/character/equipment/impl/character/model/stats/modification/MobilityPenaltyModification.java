package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialMobilityPenaltyModifier;

public class MobilityPenaltyModification implements ArmourStatsModification {

  private final BaseMaterial magicMaterial;

  public MobilityPenaltyModification(BaseMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  @Override
  public int getModifiedValue(int original) {
    int bonus = new MaterialMobilityPenaltyModifier(magicMaterial, original).getModifier();
    return Math.max(0, original - bonus);
  }
}