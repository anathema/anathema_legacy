package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private EquipmentStatisticsType statisticsType;

  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }

  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    this.statisticsType = statisticsType;
  }
}