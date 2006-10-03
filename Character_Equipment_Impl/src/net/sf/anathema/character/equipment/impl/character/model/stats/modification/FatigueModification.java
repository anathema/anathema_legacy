package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class FatigueModification {

  private final MagicalMaterial magicMaterial;
  private final IExaltedRuleSet ruleSet;

  public FatigueModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.magicMaterial = magicMaterial;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int original) {
    if (magicMaterial == MagicalMaterial.Jade) {
      return 0;
    }
    return original;
  }
}