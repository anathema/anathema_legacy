package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class MaterialFatigueModifier implements StatModifier {
  private final BaseMaterial magicMaterial;
  private final int original;

  public MaterialFatigueModifier(BaseMaterial magicMaterial, int original) {
    this.magicMaterial = magicMaterial;
    this.original = original;
  }

  @Override
  public int calculate() {
    if (magicMaterial.isJadeBased()) {
      return original;
    }
    return 0;
  }
}