package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentStatsDecorationFactory<K extends IEquipmentStats> {

  public K createRenamedPrintDecoration(IEquipmentItem item, K stats);

}