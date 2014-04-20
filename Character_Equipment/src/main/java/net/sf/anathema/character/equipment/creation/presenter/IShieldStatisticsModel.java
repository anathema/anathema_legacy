package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IShieldStatisticsModel extends IEquipmentStatisticsModel {

  IIntValueModel getCloseCombatDvBonusModel();

  IIntValueModel getRangedCombatDvBonusModel();

  IIntValueModel getMobilityPenaltyModel();

  IIntValueModel getFatigueModel();
}