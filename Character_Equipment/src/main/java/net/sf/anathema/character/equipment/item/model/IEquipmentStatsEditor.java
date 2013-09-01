package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.framework.environment.Resources;

import java.awt.Component;

public interface IEquipmentStatsEditor {

  IEquipmentStats editStats(Component parentComponent, Resources resources, String[] nameArray, IEquipmentStats selectedStats);

}
