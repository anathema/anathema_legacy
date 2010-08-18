package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

public class SoakModification implements IArmourStatsModification {

  private final MagicalMaterial magicMaterial;

  public SoakModification(MagicalMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  public int getModifiedValue(int input) {
    if (magicMaterial == MagicalMaterial.Orichalcum || magicMaterial == MagicalMaterial.Soulsteel) {
      return input + 2;
    }
    
    return input;
  }
}