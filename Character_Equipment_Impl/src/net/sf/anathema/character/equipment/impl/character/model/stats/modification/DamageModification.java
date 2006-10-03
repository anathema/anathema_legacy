package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class DamageModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public DamageModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Starmetal) {
      return input + 2;
    }
    return input;
  }
}