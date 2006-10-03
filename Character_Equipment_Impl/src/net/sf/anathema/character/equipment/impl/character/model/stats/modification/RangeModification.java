package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class RangeModification implements IStatsModification{

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public RangeModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    int modificationFactor = getModificationFactor();
    if (type == WeaponStatsType.Bow) {
      return input + 50 * modificationFactor;
    }
    if (type == WeaponStatsType.Thrown && ruleSet == ExaltedRuleSet.SecondEdition) {
      return input + 10 * modificationFactor;
    }
    return input;
  }

  private int getModificationFactor() {
    if (material == MagicalMaterial.Moonsilver) {
      return 2;
    }
    if (material == MagicalMaterial.Jade || material == MagicalMaterial.Orichalcum) {
      return 1;
    }
    return 0;
  }
}