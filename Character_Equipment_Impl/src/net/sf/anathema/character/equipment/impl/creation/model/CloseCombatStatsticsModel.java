package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.ICloseCombatStatsticsModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.SimpleIntValueModel;

public class CloseCombatStatsticsModel extends OffensiveStatisticsModel implements ICloseCombatStatsticsModel {

  private final IIntValueModel defenseModel = new SimpleIntValueModel(0);

  public IIntValueModel getDefenseModel() {
    return defenseModel;
  }
}