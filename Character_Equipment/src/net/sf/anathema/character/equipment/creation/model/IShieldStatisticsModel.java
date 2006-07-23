package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IShieldStatisticsModel {

  public IIntValueModel getCloseCombatDvBonusModel();

  public IIntValueModel getRangedCombatDvBonusModel();
}