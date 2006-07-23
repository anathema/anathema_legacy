package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IOffensiveStatisticsModel extends IEquipmentStatisticsModel {

  public IIntValueModel getSpeedModel();

  public IIntValueModel getAccuracyModel();

  public IIntValueModel getRateModel();
  
  public IWeaponDamageModel getWeaponDamageModel();
}
