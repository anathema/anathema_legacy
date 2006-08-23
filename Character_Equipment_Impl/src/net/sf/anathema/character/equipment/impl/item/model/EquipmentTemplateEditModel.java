package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.model.ItemDescription;

public class EquipmentTemplateEditModel implements IEquipmentTemplateEditModel {

  private final IItemDescription description = new ItemDescription();

  public IItemDescription getDescription() {
    return description;
  }
}