package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class DamageModification implements IStatsModification {

  private final IExaltedRuleSet ruleSet;
  private BaseMaterial material;

  public DamageModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = new BaseMaterial(material);
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    boolean isSecondEdition = ruleSet == ExaltedRuleSet.SecondEdition;
    if (material.isStarmetalBased()) {
      if (type == WeaponStatsType.Melee && isSecondEdition) {
        return input + 3;
      } else {
        return input + 2;
      }
    }
    if (material.isJadeBased() && type == WeaponStatsType.Melee && isSecondEdition) {
      return input + 1;
    }
    if (material.isOrichalcumBased() && type.isRanged() && isSecondEdition) {
      return input + 1;
    }
    return input;
  }
}