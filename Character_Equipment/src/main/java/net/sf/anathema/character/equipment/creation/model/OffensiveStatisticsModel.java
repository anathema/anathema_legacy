package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IIntValueModel;
import net.sf.anathema.character.equipment.creation.presenter.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.presenter.RangedIntValueModel;
import net.sf.anathema.lib.data.Range;

public abstract class OffensiveStatisticsModel extends EquipmentStatisticsModel implements IOffensiveStatisticsModel {

  private final IIntValueModel speedModel = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
  private final IIntValueModel accuracyModel = new RangedIntValueModel(0);
  private final IIntValueModel rateModel = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
  private final IWeaponDamageModel weaponDamageModel = new WeaponDamageModel();

  @Override
  public IIntValueModel getSpeedModel() {
    return speedModel;
  }

  @Override
  public IIntValueModel getAccuracyModel() {
    return accuracyModel;
  }

  @Override
  public IIntValueModel getRateModel() {
    return rateModel;
  }

  @Override
  public IWeaponDamageModel getWeaponDamageModel() {
    return weaponDamageModel;
  }
}