package net.sf.anathema.character.equipment.item.model;

public interface IEquipmentDatabaseManagement {

  IEquipmentTemplateEditModel getTemplateEditModel();

  IEquipmentDatabase getDatabase();

  IEquipmentStatsCreationFactory getStatsCreationFactory();
}