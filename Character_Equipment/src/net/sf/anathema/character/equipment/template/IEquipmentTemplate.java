package net.sf.anathema.character.equipment.template;

import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentTemplate {

  String getName();

  String getDescription();

  IEquipmentStats[] getStats();

  MaterialComposition getComposition();

  MagicalMaterial getMaterial();
  
  ItemCost getCost();
}