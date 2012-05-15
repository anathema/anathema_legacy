package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

public interface IEquipmentStatsEditor {

  IEquipmentStats editStats(Component parentComponent, IResources resources, String[] nameArray, IEquipmentStats selectedStats);

}
