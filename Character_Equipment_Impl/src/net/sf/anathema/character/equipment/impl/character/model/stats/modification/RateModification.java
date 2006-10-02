package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class RateModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public RateModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Orichalcum && ruleSet != ExaltedRuleSet.CoreRules) {
      return input + 1;
    }
    return input;
  }
}