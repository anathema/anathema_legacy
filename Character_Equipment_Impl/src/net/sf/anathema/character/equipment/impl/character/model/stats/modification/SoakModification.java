package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class SoakModification implements IArmourStatsModification {

  private BaseMaterial material;

  public SoakModification(MagicalMaterial magicMaterial) {
    this.material = new BaseMaterial(magicMaterial);
  }

  public int getModifiedValue(int input) {
    if (material.isOrichalcumBased() || material.isSoulsteelBased()) {
      return input + 2;
    }
    
    return input;
  }
}