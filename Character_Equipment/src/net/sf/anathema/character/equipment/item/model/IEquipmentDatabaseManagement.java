package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface IEquipmentDatabaseManagement {

  public IEquipmentTemplateEditModel getTemplateEditModel();

  public IEquipmentDatabase getDatabase();

  public IExaltedRuleSet[] getSupportedExaltedRuleSets();

  public IEquipmentStatsCreationFactory getStatsCreationFactory();
}