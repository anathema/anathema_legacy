package net.sf.anathema.character.equipment.item.model;

import java.awt.Component;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.resources.IResources;

public interface IEquipmentStatsCreationFactory {

  public IEquipmentStats createNewStats(
      Component parentComponent,
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      String[] definedNames,
      IExaltedRuleSet ruleset);

  public IEquipmentStats editStats(
      Component parentComponent,
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      String[] nameArray,
      IEquipmentStats selectedStats,
      IExaltedRuleSet ruleset);
}
