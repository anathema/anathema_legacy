package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

public class MaterialRateModifier implements StatModifier {
  private final BaseMaterial baseMaterial;
  private final WeaponStatsType type;

  public MaterialRateModifier(BaseMaterial baseMaterial, WeaponStatsType type) {
    this.baseMaterial = baseMaterial;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (baseMaterial.isOrichalcumBased() && type == WeaponStatsType.Melee) {
      return 1;
    } else {
      return 0;
    }
  }
}
