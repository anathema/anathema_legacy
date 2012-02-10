package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class RangeModification implements IStatsModification {

  private final IExaltedRuleSet ruleSet;
  private BaseMaterial material;

  public RangeModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = new BaseMaterial(material);
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    int modificationFactor = getModificationFactor();
    if (type == WeaponStatsType.Bow || type == WeaponStatsType.Thrown_BowBonuses) {
      return input + 50 * modificationFactor;
    }
    if (type == WeaponStatsType.Thrown && ruleSet == ExaltedRuleSet.SecondEdition) {
      return input + 10 * modificationFactor;
    }
    return input;
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