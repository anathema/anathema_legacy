package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;

public interface IEquipmentStatisticsCreationModel {

  public EquipmentStatisticsType getEquipmentType();

  public void setEquipmentType(EquipmentStatisticsType type);
}