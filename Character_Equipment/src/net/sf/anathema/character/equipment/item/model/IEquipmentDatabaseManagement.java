package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface IEquipmentDatabaseManagement {

  IEquipmentTemplateEditModel getTemplateEditModel();

  IEquipmentDatabase getDatabase();

  IExaltedRuleSet[] getSupportedExaltedRuleSets();

  IEquipmentStatsCreationFactory getStatsCreationFactory();
}