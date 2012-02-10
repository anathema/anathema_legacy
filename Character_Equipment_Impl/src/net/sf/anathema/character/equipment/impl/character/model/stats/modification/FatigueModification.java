package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class FatigueModification implements IArmourStatsModification {

  private final BaseMaterial magicMaterial;

  public FatigueModification(MagicalMaterial magicMaterial) {
    this.magicMaterial = new BaseMaterial(magicMaterial);
  }

  public int getModifiedValue(int original) {
    if (magicMaterial.isJadeBased()) {
      return 0;
    }
    return original;
  }
}