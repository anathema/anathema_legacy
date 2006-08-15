package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface ICloseCombatStatsticsModel extends IOffensiveStatisticsModel {

  public IIntValueModel getDefenseModel();
}