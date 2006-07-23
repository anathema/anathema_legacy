package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentTemplate {

  public String getDescription();

  public IEquipmentStats[] getEquipmentStats();

  public String getName();
}