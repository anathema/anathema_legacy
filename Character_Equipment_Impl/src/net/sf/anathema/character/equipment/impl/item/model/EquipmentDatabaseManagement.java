package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;

public class EquipmentDatabaseManagement implements IEquipmentDatabaseManagement, IItemData {

  private final EquipmentTemplateEditModel templateEditModel;
  private final IEquipmentDatabase database;
  private final IEquipmentStatsCreationFactory statsFactory;

  public EquipmentDatabaseManagement(IEquipmentDatabase database) {
    this.database = database;
    this.templateEditModel = new EquipmentTemplateEditModel(database);
    this.statsFactory = new EquipmentStatsCreationFactory(database.getCollectionFactory());
  }

  public IEquipmentTemplateEditModel getTemplateEditModel() {
    return templateEditModel;
  }

  public IEquipmentDatabase getDatabase() {
    return database;
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    // nothing to do
  }

  public IExaltedRuleSet[] getSupportedExaltedRuleSets() {
    return ExaltedRuleSet.values();
  }

  public IEquipmentStatsCreationFactory getStatsCreationFactory() {
    return statsFactory;
  }
}