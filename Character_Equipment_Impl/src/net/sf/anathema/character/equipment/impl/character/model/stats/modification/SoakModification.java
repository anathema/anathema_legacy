package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.generic.health.HealthType;

public class SoakModification implements IArmourStatsModification {

  private BaseMaterial material;
  private HealthType healthType;

  public SoakModification(BaseMaterial magicMaterial, HealthType healthType) {
    this.healthType = healthType;
    this.material = magicMaterial;
  }

  @Override
  public int getModifiedValue(int input) {
    if (material.isOrichalcumBased() || material.isSoulsteelBased()) {
      return input + 2;
    }
    if (material.isAdamantBased() && healthType != HealthType.Bashing) {
      return input + 3;
    }
    return input;
  }
}