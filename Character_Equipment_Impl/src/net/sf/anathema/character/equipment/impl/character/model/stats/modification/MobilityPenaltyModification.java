package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class MobilityPenaltyModification implements IArmourStatsModification {

  private final BaseMaterial magicMaterial;
  private IExaltedRuleSet ruleSet;

  public MobilityPenaltyModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
    this.magicMaterial = new BaseMaterial(magicMaterial);
  }

  public int getModifiedValue(int original) {
    if (magicMaterial.isMoonsilverBased()) {
      return 0;
    }
    if (magicMaterial.isAdamantBased() && ruleSet == ExaltedRuleSet.SecondEdition) {
      return original - 1;
    }
    return original;
  }
}