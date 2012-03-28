package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class MobilityPenaltyModification implements IArmourStatsModification {

  private final BaseMaterial magicMaterial;

  public MobilityPenaltyModification(BaseMaterial magicMaterial) {
    this.magicMaterial = magicMaterial;
  }

  @Override
  public int getModifiedValue(int original) {
    if (magicMaterial.isMoonsilverBased()) {
      return 0;
    }
    if (magicMaterial.isAdamantBased()) {
      return original - 1;
    }
    return original;
  }
}