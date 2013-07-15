package net.sf.anathema.equipment.core;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;

public interface IEquipmentTemplate {

  String getName();

  String getDescription();

  IEquipmentStats[] getStatsList();

  MaterialComposition getComposition();

  MagicalMaterial getMaterial();
  
  ItemCost getCost();
}