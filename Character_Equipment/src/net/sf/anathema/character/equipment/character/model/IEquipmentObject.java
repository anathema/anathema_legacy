package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipment;

public interface IEquipmentObject {

  public String getName();

  public String getDescription();

  public IEquipment[] getEquipments();

  public void setPrintEnabled(IEquipment equipment, boolean enabled);
}