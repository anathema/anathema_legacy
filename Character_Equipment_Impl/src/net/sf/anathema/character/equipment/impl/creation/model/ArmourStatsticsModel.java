package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IArmourStatisticsModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.SimpleIntValueModel;

public class ArmourStatsticsModel extends EquipmentStatisticsModel implements IArmourStatisticsModel {

  private final IIntValueModel bashingHardness = new SimpleIntValueModel(0);
  private final IIntValueModel bashingSoak = new SimpleIntValueModel(0);
  private final IIntValueModel fatigue = new SimpleIntValueModel(0);
  private final IIntValueModel lethalHardness = new SimpleIntValueModel(0);
  private final IIntValueModel lethalSoak = new SimpleIntValueModel(0);
  private final IIntValueModel mobilityPenalty = new SimpleIntValueModel(0);

  public IIntValueModel getBashingHardnessModel() {
    return bashingHardness;
  }

  public IIntValueModel getBashingSoakModel() {
    return bashingSoak;
  }

  public IIntValueModel getFatigueModel() {
    return fatigue;
  }

  public IIntValueModel getLethalHardnessModel() {
    return lethalHardness;
  }

  public IIntValueModel getLethalSoakModel() {
    return lethalSoak;
  }

  public IIntValueModel getMobilityPenaltyModel() {
    return mobilityPenalty;
  }
}