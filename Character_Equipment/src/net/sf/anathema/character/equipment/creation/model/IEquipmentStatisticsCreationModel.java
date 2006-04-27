package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentStatisticsCreationModel {

  public EquipmentStatisticsType getEquipmentType();

  public void setEquipmentType(EquipmentStatisticsType type);
  
  public ICloseCombatStatsticsModel getCloseCombatStatsticsModel();

  public void addEquipmentTypeChangeListener(IChangeListener changeListener);
}