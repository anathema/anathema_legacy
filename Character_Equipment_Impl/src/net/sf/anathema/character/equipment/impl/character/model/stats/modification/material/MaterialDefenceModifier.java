package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class MaterialDefenceModifier implements StatModifier {
  private final BaseMaterial material;

  public MaterialDefenceModifier(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int calculate() {
    if (material.isOrichalcumBased()) {
      return 1;
    }
    if (material.isMoonsilverBased()) {
      return 2;
    }
    return 0;
  }
}
