package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.framework.itemdata.model.IItemData;

public interface IEquipmentDatabase extends IItemData {

  public IEquipmentTemplateEditModel getTemplateEditModel();

  public String[] getAllAvailableTemplateIds();
}