package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SpeedModification implements IStatsModification {

  private final MagicalMaterial material;
  private final IExaltedRuleSet ruleSet;
  private BaseMaterial baseMaterial;

  public SpeedModification(MagicalMaterial material, IExaltedRuleSet ruleSet) {
    this.material = material;
    this.baseMaterial = new BaseMaterial(material);
    this.ruleSet = ruleSet;
  }

  public int getModifiedValue(int input, WeaponStatsType type) {
    if (baseMaterial.isJadeBased() && ruleSet == ExaltedRuleSet.SecondEdition) {
      return Math.max(1, input - 1);
    }
    if (material == MagicalMaterial.Jade && type == WeaponStatsType.Melee) {
      return input + 3;
    }
    if (material == MagicalMaterial.Orichalcum && ruleSet == ExaltedRuleSet.CoreRules && !type.isRanged()) {
      return input + 1;
    }
    return input;
  }
}