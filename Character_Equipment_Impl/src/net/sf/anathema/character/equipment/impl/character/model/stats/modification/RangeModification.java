package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class RangeModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public RangeModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Moonsilver && type == WeaponStatsType.Bow) {
      return input + 100;
    }
    return input;
  }
}