package net.sf.anathema.character.equipment.impl.character.model.stats.modification.material;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;

public class MaterialRangeModifier implements StatModifier {
  private final BaseMaterial material;
  private final WeaponStatsType type;

  public MaterialRangeModifier(BaseMaterial material, WeaponStatsType type) {
    this.material = material;
    this.type = type;
  }

  @Override
  public int calculate() {
    int modificationFactor = getModificationFactor();
    if (type == WeaponStatsType.Bow || type == WeaponStatsType.Thrown_BowBonuses) {
      return 50 * modificationFactor;
    } else if (type == WeaponStatsType.Thrown) {
      return 10 * modificationFactor;
    } else {
      return 0;
    }
  }

  private int getModificationFactor() {
    if (material.isMoonsilverBased()) {
      return 2;
    }
    if (material.isJadeBased() || material.isOrichalcumBased()) {
      return 1;
    }
    return 0;
  }
}