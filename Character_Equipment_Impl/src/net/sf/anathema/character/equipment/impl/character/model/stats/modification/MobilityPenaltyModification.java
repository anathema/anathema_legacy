package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class MobilityPenaltyModification {

  private final MagicalMaterial magicMaterial;
  private final IExaltedRuleSet ruleSet;

  public MobilityPenaltyModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet) {
    this.magicMaterial = magicMaterial;
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int original) {
    if (magicMaterial == MagicalMaterial.Moonsilver) {
      return 0;
    }
    return original;
  }
}