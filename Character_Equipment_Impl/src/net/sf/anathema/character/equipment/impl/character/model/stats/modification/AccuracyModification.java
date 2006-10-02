package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class AccuracyModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public AccuracyModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Orichalcum) {
      return input + 1;
    }
    if (material == MagicalMaterial.Moonsilver) {
      return type.isRanged() ? input + 1 : input+ 2;
    }
    return input;
  }
}