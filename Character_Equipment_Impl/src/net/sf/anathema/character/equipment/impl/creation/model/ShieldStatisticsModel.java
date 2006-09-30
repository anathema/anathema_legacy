package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IShieldStatisticsModel;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class ShieldStatisticsModel extends EquipmentStatisticsModel implements IShieldStatisticsModel {

  private final IIntValueModel closeCombatBonusModel = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel rangedCombatBonusModel = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel fatigue = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel mobilityPenalty = new RangedIntValueModel(new Range(Integer.MIN_VALUE, 0), 0);

  public IIntValueModel getCloseCombatDvBonusModel() {
    return closeCombatBonusModel;
  }

  public IIntValueModel getRangedCombatDvBonusModel() {
    return rangedCombatBonusModel;
  }

  public IIntValueModel getFatigueModel() {
    return fatigue;
  }

  public IIntValueModel getMobilityPenaltyModel() {
    return mobilityPenalty;
  }
}