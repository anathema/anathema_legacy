package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;

public class EquipmentDatabaseManagement implements IEquipmentDatabaseManagement {

  private final EquipmentTemplateEditModel templateEditModel;
  private final IEquipmentDatabase database;
  private final EquipmentStatsFactory statsFactory;
  private final SwingStatsEditor statsEditor;

  public EquipmentDatabaseManagement(IEquipmentDatabase database) {
    this.database = database;
    this.templateEditModel = new EquipmentTemplateEditModel(database);
    this.statsFactory = new SimpleEquipmentStatsFactory(database.getCollectionFactory());
    this.statsEditor = new SwingStatsEditor(database.getCollectionFactory());
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
  public EquipmentStatsFactory getStatsCreationFactory() {
    return statsFactory;
  }

  @Override
  public StatsEditor getStatsEditor() {
    return statsEditor;
  }
}