package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class HardnessModification implements IArmourStatsModification {

  private BaseMaterial material;

  public HardnessModification(MagicalMaterial magicMaterial) {
    this.material = new BaseMaterial(magicMaterial);
  }

  @Override
  public int getModifiedValue(int original) {
    if ((material.isOrichalcumBased() || material.isSoulsteelBased() || material.isStarmetalBased())) {
      return original + 1;
    }
    return original;
  }
}