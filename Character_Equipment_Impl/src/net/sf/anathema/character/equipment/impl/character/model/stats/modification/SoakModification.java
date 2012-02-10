package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SoakModification implements IArmourStatsModification {

  private BaseMaterial material;
  private IExaltedRuleSet ruleSet;

  public SoakModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
    this.material = new BaseMaterial(magicMaterial);
  }

  public int getModifiedValue(int input) {
    if (material.isOrichalcumBased() || material.isSoulsteelBased()) {
      return input + 2;
    }
    if (material.isAdamantBased() && ruleSet == ExaltedRuleSet.SecondEdition) {
      return input + 3;
    }
    return input;
  }
}