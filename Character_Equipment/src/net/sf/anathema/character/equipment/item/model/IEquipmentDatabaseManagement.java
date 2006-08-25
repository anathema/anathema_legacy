package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.framework.itemdata.model.IItemData;

public interface IEquipmentDatabaseManagement extends IItemData {

  public IEquipmentTemplateEditModel getTemplateEditModel();
  
  public IEquipmentDatabase getDatabase();
}