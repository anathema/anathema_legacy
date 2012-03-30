package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

public class MaterialDamageModifier implements StatModifier {
  private final BaseMaterial material;
  private final WeaponStatsType type;

  public MaterialDamageModifier(BaseMaterial material, WeaponStatsType type) {
    this.material = material;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (material.isStarmetalBased()) {
      if (type == WeaponStatsType.Melee) {
        return 3;
      } else {
        return 2;
      }
    }
    if (material.isJadeBased() && type == WeaponStatsType.Melee) {
      return 1;
    }
    if (material.isOrichalcumBased() && type.isRanged()) {
      return 1;
    }
    return 0;
  }
}