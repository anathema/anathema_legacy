package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IOffensiveStatisticsModel extends IEquipmentStatisticsModel {

  IIntValueModel getSpeedModel();

  IIntValueModel getAccuracyModel();

  IIntValueModel getRateModel();

  IWeaponDamageModel getWeaponDamageModel();

  boolean supportsRate();
}
