package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class HardnessModification {

  private final MagicalMaterial magicMaterial;
  private final IExaltedRuleSet ruleSet;

  public HardnessModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.magicMaterial = magicMaterial;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int original) {
    if ((magicMaterial == MagicalMaterial.Orichalcum || magicMaterial == MagicalMaterial.Soulsteel || magicMaterial == MagicalMaterial.Starmetal)
        && ruleSet == ExaltedRuleSet.SecondEdition) {
      return original + 1;
    }
    return original;
  }
}