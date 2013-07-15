package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;

public interface IEquipmentStatsDecorationFactory<K extends IEquipmentStats> {

  K createRenamedPrintDecoration(IEquipmentItem item, K stats);

}