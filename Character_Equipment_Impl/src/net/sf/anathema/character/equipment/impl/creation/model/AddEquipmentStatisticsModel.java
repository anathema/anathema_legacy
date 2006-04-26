package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IAddEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;

public class AddEquipmentStatisticsModel implements IAddEquipmentStatisticsModel {

  private EquipmentStatisticsType statisticsType;

  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }

  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    this.statisticsType = statisticsType;
  }
}