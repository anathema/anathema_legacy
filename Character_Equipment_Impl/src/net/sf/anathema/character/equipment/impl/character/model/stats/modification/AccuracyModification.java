package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class AccuracyModification implements IStatsModification {

  private final IExaltedRuleSet ruleSet;
  private BaseMaterial material;

  public AccuracyModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = new BaseMaterial(material);
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    boolean isSecondEdition = ruleSet.getEdition() == ExaltedEdition.SecondEdition;
    if (material.isSoulsteelBased()) {
      if (isSecondEdition || type.isRanged()) {
        return input + 2;
      } else {
        return input + 1;
      }
    }
    if (material.isOrichalcumBased()) {
      return input + 1;
    }
    if (material.isMoonsilverBased()) {
      if (type.isRanged()) {
        return input + 1;
      } else {
        return input + 2;
      }
    }
    if (material.isStarmetalBased() && isSecondEdition) {
      return input + 1;
    }
    return input;
  }
}