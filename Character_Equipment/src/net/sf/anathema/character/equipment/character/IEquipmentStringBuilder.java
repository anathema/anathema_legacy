package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentStringBuilder {

  public String createString(IEquipmentItem item, IEquipmentStats equipment);

}