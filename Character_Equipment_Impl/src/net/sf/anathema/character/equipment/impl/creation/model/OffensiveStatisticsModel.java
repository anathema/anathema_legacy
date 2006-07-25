package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.IWeaponDamageModel;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class OffensiveStatisticsModel extends EquipmentStatisticsModel implements IOffensiveStatisticsModel {

  private final IIntValueModel speedModel = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
  private final IIntValueModel accuracyModel = new RangedIntValueModel(0);
  private final IIntValueModel rateModel = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
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