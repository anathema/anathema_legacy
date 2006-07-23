package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IEquipmentItem {

  public String getName();

  public String getDescription();

  public IEquipmentStats[] getEquipments();

  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled);

  public boolean isPrintEnabled(IEquipmentStats stats);
}