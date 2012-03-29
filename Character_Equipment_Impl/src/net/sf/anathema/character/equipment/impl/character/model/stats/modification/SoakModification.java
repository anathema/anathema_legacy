package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialSoakModifier;
import net.sf.anathema.character.generic.health.HealthType;

public class SoakModification implements ArmourStatsModification {

  private BaseMaterial material;
  private HealthType healthType;

  public SoakModification(BaseMaterial magicMaterial, HealthType healthType) {
    this.healthType = healthType;
    this.material = magicMaterial;
  }

  @Override
  public int getModifiedValue(int input) {
    int modifier = new MaterialSoakModifier(material, healthType).getModifier();
    return input + modifier;
  }
}