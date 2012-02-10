package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class HardnessModification implements IArmourStatsModification {

  private final IExaltedRuleSet ruleSet;
  private BaseMaterial material;

  public HardnessModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.material = new BaseMaterial(magicMaterial);
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int original) {
    if ((material.isOrichalcumBased() || material.isSoulsteelBased() || material.isStarmetalBased())
            && ruleSet == ExaltedRuleSet.SecondEdition) {
      return original + 1;
    }
    return original;
  }
}