package net.sf.anathema.character.equipment.creation.presenter;

public interface IOffensiveStatisticsModel extends IEquipmentStatisticsModel {

  IIntValueModel getSpeedModel();

  IIntValueModel getAccuracyModel();

  IIntValueModel getRateModel();

  IWeaponDamageModel getWeaponDamageModel();
}