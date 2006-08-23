package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface IEquipmentDatabase extends IItemData {

  public IEquipmentTemplateEditModel getTemplateEditModel();

  public IEquipmentTemplate[] getAllAvailableTemplates();
}