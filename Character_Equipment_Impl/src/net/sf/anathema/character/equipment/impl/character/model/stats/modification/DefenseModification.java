package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class DefenseModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;

  public DefenseModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material == MagicalMaterial.Orichalcum) {
      return input + 1;
    }
    if (material == MagicalMaterial.Moonsilver && ruleSet.getEdition() == ExaltedEdition.SecondEdition) {
      return input + 2;
    }
    return input;
  }
}