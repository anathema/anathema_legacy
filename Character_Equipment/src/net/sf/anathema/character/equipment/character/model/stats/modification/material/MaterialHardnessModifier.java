package net.sf.anathema.character.equipment.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.character.model.stats.modification.StatModifier;

public class MaterialHardnessModifier implements StatModifier {
  private final BaseMaterial material;

  public MaterialHardnessModifier(BaseMaterial material) {
    this.material = material;
  }

  @Override
  public int calculate() {
    if ((material.isOrichalcumBased() || material.isSoulsteelBased() || material.isStarmetalBased())) {
      return 1;
    }
    return 0;
  }
}