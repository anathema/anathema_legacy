package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IRangedCombatStatisticsModel extends IOffensiveStatisticsModel {

  IIntValueModel getRangeModel();
}