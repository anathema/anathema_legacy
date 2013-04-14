package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.resources.Resources;

public interface StatsEditor {
  IEquipmentStats editStats(Resources resources, String[] nameArray, IEquipmentStats selectedStats);
}