package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;

public interface IEquipmentStatisticsCreationModel {

  void setEquipmentType(EquipmentStatisticsType type);

  ICloseCombatStatsticsModel getCloseCombatStatsticsModel();

  IRangedCombatStatisticsModel getRangedWeaponStatisticsModel();

  IArmourStatisticsModel getArmourStatisticsModel();
  
  IArtifactStatisticsModel getArtifactStatisticsModel();
  
  ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel();

  IWeaponTagsModel getWeaponTagsModel();

  EquipmentStatisticsType getEquipmentType();

  boolean isNameUnique(String name);

  void setForbiddenNames(String[] definedNames);
}