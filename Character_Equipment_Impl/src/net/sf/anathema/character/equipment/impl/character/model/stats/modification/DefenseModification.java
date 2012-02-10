package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class DefenseModification implements IStatsModification {

  private final IExaltedRuleSet ruleSet;
  private BaseMaterial material;

  public DefenseModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = new BaseMaterial(material);
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (material.isOrichalcumBased()) {
      return input + 1;
    }
    if (material.isMoonsilverBased() && ruleSet.getEdition() == ExaltedEdition.SecondEdition) {
      return input + 2;
    }
    return input;
  }
}