package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IArmourStatisticsModel;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class ArmourStatsticsModel extends EquipmentStatisticsModel implements IArmourStatisticsModel {

  private final IIntValueModel bashingHardness = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel bashingSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel fatigue = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalHardness = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel mobilityPenalty = new RangedIntValueModel(new Range(Integer.MIN_VALUE, 0), 0);

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