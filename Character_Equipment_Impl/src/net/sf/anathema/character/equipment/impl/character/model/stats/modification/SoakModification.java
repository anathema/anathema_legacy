package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SoakModification {

  private final MagicalMaterial magicMaterial;
  private final IExaltedRuleSet ruleSet;

  public SoakModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.magicMaterial = magicMaterial;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input) {
    if (magicMaterial == MagicalMaterial.Orichalcum || magicMaterial == MagicalMaterial.Soulsteel) {
      return input + 2;
    }
    return input;
  }
}