package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public abstract class AbstractEquipmentTemplate implements IEquipmentTemplate {

  public final boolean isSupported(IExaltedRuleSet ruleSet) {
    return getStats(ruleSet).length != 0;
  }
}