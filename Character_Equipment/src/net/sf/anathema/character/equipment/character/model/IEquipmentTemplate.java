package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface IEquipmentTemplate {

  public String getName();

  public String getDescription();

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet);
}