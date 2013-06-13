package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.lib.data.Condition;

public class DirtyEquipmentCondition implements Condition {
  private IEquipmentDatabaseManagement model;

  public DirtyEquipmentCondition(IEquipmentDatabaseManagement model) {
    this.model = model;
  }

  @Override
  public boolean isFulfilled() {
    return model.getTemplateEditModel().isDirty();
  }
}