package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class FatigueModification implements IArmourStatsModification {

  private final BaseMaterial magicMaterial;

  public FatigueModification(BaseMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  @Override
  public int getModifiedValue(int original) {
    if (magicMaterial.isJadeBased()) {
      return 0;
    }
    return original;
  }
}