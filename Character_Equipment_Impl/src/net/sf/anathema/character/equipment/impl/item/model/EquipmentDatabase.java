package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.framework.itemdata.model.BasicItemData;

public class EquipmentDatabase extends BasicItemData implements IEquipmentDatabase {

  private IEquipmentTemplateEditModel templateEditModel = new EquipmentTemplateEditModel();

  public IEquipmentTemplateEditModel getTemplateEditModel() {
    return templateEditModel;
  }

  public String[] getAllAvailableTemplateIds() {
    return new String[0];
  }
}