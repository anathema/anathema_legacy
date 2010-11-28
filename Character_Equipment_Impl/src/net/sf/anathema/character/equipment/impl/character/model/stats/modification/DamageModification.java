package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class DamageModification implements IStatsModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public DamageModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    boolean isSecondEdition = ruleSet == ExaltedRuleSet.SecondEdition;
    if (material == MagicalMaterial.Starmetal) {
      if (type == WeaponStatsType.Melee && isSecondEdition) {
        return input + 3;
      } else {
        return input + 2;
      }
    }
    if (material == MagicalMaterial.Jade && type == WeaponStatsType.Melee && isSecondEdition) {
      return input + 1;
    }
    if (material == MagicalMaterial.Orichalcum && type.isRanged() && isSecondEdition) {
      return input + 1;
    }
    return input;
  }
}