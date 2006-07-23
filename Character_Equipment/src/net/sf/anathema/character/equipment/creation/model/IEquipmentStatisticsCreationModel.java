package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentStatisticsCreationModel {

  public void setEquipmentType(EquipmentStatisticsType type);

  public ICloseCombatStatsticsModel getCloseCombatStatsticsModel();
  
  public IRangedCombatStatisticsModel getRangedWeaponStatisticsModel();

  public void addEquipmentTypeChangeListener(IChangeListener changeListener);

  public boolean isEquipmentTypeSelected(EquipmentStatisticsType type);

  public IWeaponTagsModel getWeaponTagsModel();
}