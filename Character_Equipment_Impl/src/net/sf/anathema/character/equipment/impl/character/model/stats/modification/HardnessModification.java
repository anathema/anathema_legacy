package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class HardnessModification implements IArmourStatsModification {

  private BaseMaterial material;

  public HardnessModification(BaseMaterial magicMaterial) {
    this.material = magicMaterial;
  }

  @Override
  public int getModifiedValue(int original) {
    if ((material.isOrichalcumBased() || material.isSoulsteelBased() || material.isStarmetalBased())) {
      return original + 1;
    }
    return original;
  }
}