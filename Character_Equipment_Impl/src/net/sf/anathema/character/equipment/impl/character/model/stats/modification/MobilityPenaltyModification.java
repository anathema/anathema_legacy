package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class MobilityPenaltyModification implements IArmourStatsModification {

  private final BaseMaterial magicMaterial;

  public MobilityPenaltyModification(MagicalMaterial magicMaterial) {
    this.magicMaterial = new BaseMaterial(magicMaterial);
  }

  public int getModifiedValue(int original) {
    if (magicMaterial.isMoonsilverBased()) {
      return 0;
    }
    return original;
  }
}