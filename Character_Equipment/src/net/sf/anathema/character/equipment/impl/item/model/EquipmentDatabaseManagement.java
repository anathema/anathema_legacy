package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;

public class EquipmentDatabaseManagement implements IEquipmentDatabaseManagement {

  private final EquipmentTemplateEditModel templateEditModel;
  private final IEquipmentDatabase database;
  private final EquipmentStatsFactory statsFactory = new SimpleEquipmentStatsFactory();
  private final SwingStatsEditor statsEditor = new SwingStatsEditor();

  public EquipmentDatabaseManagement(IEquipmentDatabase database) {
    this.database = database;
    this.templateEditModel = new EquipmentTemplateEditModel(database);
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