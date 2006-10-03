package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class MobilityPenaltyModification implements IArmourStatsModification {

  private final MagicalMaterial magicMaterial;

  public MobilityPenaltyModification(MagicalMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  public int getModifiedValue(int original) {
    if (magicMaterial == MagicalMaterial.Moonsilver) {
      return 0;
    }
    return original;
  }
}