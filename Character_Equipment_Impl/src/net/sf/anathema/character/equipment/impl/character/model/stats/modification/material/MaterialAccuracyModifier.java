package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

public class MaterialAccuracyModifier implements StatModifier {
  private final BaseMaterial material;
  private final WeaponStatsType type;

  public MaterialAccuracyModifier(BaseMaterial material, WeaponStatsType type) {
    this.material = material;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (material.isSoulsteelBased()) {
      return 2;
    }
    if (material.isOrichalcumBased() || material.isMoonsilverBased()) {
      if (type.isRanged()) {
        return 1;
      } else {
        return 2;
      }
    }
    if (material.isStarmetalBased()) {
      return 1;
    }
    return 0;
  }
}