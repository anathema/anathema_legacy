package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IShieldStatisticsModel extends IEquipmentStatisticsModel {

  public IIntValueModel getCloseCombatDvBonusModel();

  public IIntValueModel getRangedCombatDvBonusModel();

  public IIntValueModel getMobilityPenaltyModel();

  public IIntValueModel getFatigueModel();
}