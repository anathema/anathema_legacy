package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  private static final String NATURAL = "Natural"; //$NON-NLS-1$

  public String getDescription() {
    return NATURAL;
  }

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet) {
    final IEquipmentStats[][] stats = new IEquipmentStats[1][];
    ruleSet.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        stats[0] = INaturalWeaponConstants.CORE_RULES;
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        stats[0] = INaturalWeaponConstants.POWER_COMBAT;
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        stats[0] = INaturalWeaponConstants.SECOND_EDITION;
      }
    });
    return stats[0];
  }

  public String getName() {
    return NATURAL;
  }

  public MaterialComposition getComposition() {
    return MaterialComposition.None;
  }

  public MagicalMaterial getMaterial() {
    return null;
  }
}