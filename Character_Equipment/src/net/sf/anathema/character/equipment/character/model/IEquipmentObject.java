package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentObject {

  public String getName();

  public String getDescription();

  public IEquipmentStats[] getEquipments();

  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled);
}