package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class MaterialMobilityPenaltyModifier implements StatModifier {

  private final BaseMaterial magicMaterial;
  private final int original;

  public MaterialMobilityPenaltyModifier(BaseMaterial magicMaterial, int original) {
    this.magicMaterial = magicMaterial;
    this.original = original;
  }

  @Override
  public int calculate() {
    if (magicMaterial.isMoonsilverBased()) {
      return original;
    }
    if (magicMaterial.isAdamantBased()) {
      return -1;
    }
    return 0;
  }
}
