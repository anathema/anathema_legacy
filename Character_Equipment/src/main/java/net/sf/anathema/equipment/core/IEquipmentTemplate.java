package net.sf.anathema.equipment.core;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

public interface IEquipmentTemplate {

  String getName();

  String getDescription();

  IEquipmentStats[] getStatsList();

  MaterialComposition getComposition();

  MagicalMaterial getMaterial();
  
  ItemCost getCost();
}