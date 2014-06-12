package net.sf.anathema.equipment.core;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

import java.util.Collection;

public interface IEquipmentTemplate {

  String getName();

  String getDescription();

  Collection<IEquipmentStats> getStatsList();

  MaterialComposition getComposition();

  MagicalMaterial getMaterial();
  
  ItemCost getCost();
}