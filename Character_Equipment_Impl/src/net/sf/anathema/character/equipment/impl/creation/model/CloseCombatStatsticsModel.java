package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class CloseCombatStatsticsModel extends OffensiveStatisticsModel implements ICloseCombatStatsticsModel {

  private final IIntValueModel defenseModel = new RangedIntValueModel(0);

  public IIntValueModel getDefenseModel() {
    return defenseModel;
  }
}