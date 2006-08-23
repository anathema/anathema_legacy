package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.itemdata.model.BasicItemData;

public class EquipmentDatabase extends BasicItemData implements IEquipmentDatabase {

  private IEquipmentTemplateEditModel templateEditModel = new EquipmentTemplateEditModel();

  public IEquipmentTemplateEditModel getTemplateEditModel() {
    return templateEditModel;
  }

  public IEquipmentTemplate[] getAllAvailableTemplates() {
    return new IEquipmentTemplate[0];
  }
}