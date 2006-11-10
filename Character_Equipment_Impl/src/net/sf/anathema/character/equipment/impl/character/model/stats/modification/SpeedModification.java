package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SpeedModification implements IStatsModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public SpeedModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Jade && ruleSet == ExaltedRuleSet.SecondEdition) {
      return Math.max(1, input - 1);
    }
    if (material == MagicalMaterial.Jade && type == WeaponStatsType.Melee) {
      return input + 3;
    }
    if (material == MagicalMaterial.Orichalcum && ruleSet == ExaltedRuleSet.CoreRules && !type.isRanged()) {
      return input + 1;
    }
    return input;
  }
}