package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class MaterialSpeedModifier implements StatModifier {
  private final BaseMaterial baseMaterial;

  public MaterialSpeedModifier(BaseMaterial baseMaterial) {
    this.baseMaterial = baseMaterial;
  }

  @Override
  public int calculate() {
    if (baseMaterial.isJadeBased()) {
      return 1;
    } else {
      return 0;
    }
  }
}