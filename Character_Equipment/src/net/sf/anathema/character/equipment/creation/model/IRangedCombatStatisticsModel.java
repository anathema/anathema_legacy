package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IRangedCombatStatisticsModel extends IOffensiveStatisticsModel {

  public IIntValueModel getRangeModel();
}