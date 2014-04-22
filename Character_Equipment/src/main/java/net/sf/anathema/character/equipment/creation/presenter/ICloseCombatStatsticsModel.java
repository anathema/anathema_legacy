package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface ICloseCombatStatsticsModel extends IOffensiveStatisticsModel {

  IIntValueModel getDefenseModel();
}