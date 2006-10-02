package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SpeedModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public SpeedModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Orichalcum && !type.isRanged()) {
      return input + 1;
    }
    return input;
  }
}