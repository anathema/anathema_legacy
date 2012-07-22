package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.IChangeListener;

public interface IEquipmentStatisticsCreationModel {

  void setEquipmentType(EquipmentStatisticsType type);

  ICloseCombatStatsticsModel getCloseCombatStatsticsModel();

  IRangedCombatStatisticsModel getRangedWeaponStatisticsModel();

  IArmourStatisticsModel getArmourStatisticsModel();
  
  IArtifactStatisticsModel getArtifactStatisticsModel();
  
  ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel();

  void addEquipmentTypeChangeListener(IChangeListener changeListener);

  boolean isEquipmentTypeSelected(EquipmentStatisticsType type);
  
  IWeaponTagsModel getWeaponTagsModel();

  EquipmentStatisticsType getEquipmentType();

  boolean isNameUnique(String name);
}