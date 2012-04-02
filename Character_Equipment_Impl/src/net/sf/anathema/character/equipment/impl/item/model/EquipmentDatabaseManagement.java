package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;

public class EquipmentDatabaseManagement implements IEquipmentDatabaseManagement {

  private final EquipmentTemplateEditModel templateEditModel;
  private final IEquipmentDatabase database;
  private final IEquipmentStatsCreationFactory statsFactory;

  public EquipmentDatabaseManagement(IEquipmentDatabase database) {
    this.database = database;
    this.templateEditModel = new EquipmentTemplateEditModel(database);
    this.statsFactory = new EquipmentStatsCreationFactory(database.getCollectionFactory());
  }

  @Override
  public IEquipmentTemplateEditModel getTemplateEditModel() {
    return templateEditModel;
  }

  @Override
  public IEquipmentDatabase getDatabase() {
    return database;
  }

  @Override
  public IEquipmentStatsCreationFactory getStatsCreationFactory() {
    return statsFactory;
  }
}