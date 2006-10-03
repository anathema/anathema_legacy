package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class FatigueModification implements IArmourStatsModification {

  private final MagicalMaterial magicMaterial;

  public FatigueModification(MagicalMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  public int getModifiedValue(int original) {
    if (magicMaterial == MagicalMaterial.Jade) {
      return 0;
    }
    return original;
  }
}