package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IArmourStatisticsModel extends IEquipmentStatisticsModel {

  public IIntValueModel getBashingSoakModel();

  public IIntValueModel getLethalSoakModel();

  public IIntValueModel getBashingHardnessModel();

  public IIntValueModel getLethalHardnessModel();
  
  /** Mobility penalty is modelled as negativ number. */
  public IIntValueModel getMobilityPenaltyModel();

  public IIntValueModel getFatigueModel();
}