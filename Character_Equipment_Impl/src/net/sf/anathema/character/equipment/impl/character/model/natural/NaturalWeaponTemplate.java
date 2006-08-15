package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  private IEquipmentStats[] equipmentStats = new IEquipmentStats[] { new Punch(), new Kick(), new Clinch() };

  public String getDescription() {
    return "The Characters natural weapons";
  }

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet) {
    if (ruleSet != ExaltedRuleSet.SecondEdition) {
      return new IEquipmentStats[0];
    }
    return equipmentStats;
  }

  public String getName() {
    return "Natural Weapons";
  }
}