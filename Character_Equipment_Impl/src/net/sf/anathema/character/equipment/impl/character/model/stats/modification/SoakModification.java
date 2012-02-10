package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SoakModification implements IArmourStatsModification {

  private BaseMaterial material;
  private IExaltedRuleSet ruleSet;
  private HealthType healthType;

  public SoakModification(MagicalMaterial magicMaterial, IExaltedRuleSet ruleSet, HealthType healthType) {
    this.ruleSet = ruleSet;
    this.healthType = healthType;
    this.material = new BaseMaterial(magicMaterial);
  }

  public int getModifiedValue(int input) {
    if (material.isOrichalcumBased() || material.isSoulsteelBased()) {
      return input + 2;
    }
    if (material.isAdamantBased() && ruleSet == ExaltedRuleSet.SecondEdition && healthType != HealthType.Bashing) {
      return input + 3;
    }
    return input;
  }
}