package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.IWeaponDamageModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.SimpleIntValueModel;

public class OffensiveStatisticsModel extends EquipmentStatisticsModel implements IOffensiveStatisticsModel {

  private final IIntValueModel speedModel = new SimpleIntValueModel(1);
  private final IIntValueModel accuracyModel = new SimpleIntValueModel(0);
  private final IIntValueModel rateModel = new SimpleIntValueModel(1);
  private final IWeaponDamageModel weaponDamageModel = new WeaponDamageModel();

  public IIntValueModel getSpeedModel() {
    return speedModel;
  }

  public IIntValueModel getAccuracyModel() {
    return accuracyModel;
  }

  public IIntValueModel getRateModel() {
    return rateModel;
  }

  public IWeaponDamageModel getWeaponDamageModel() {
    return weaponDamageModel;
  }
}