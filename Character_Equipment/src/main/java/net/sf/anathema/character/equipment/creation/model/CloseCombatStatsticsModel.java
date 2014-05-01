package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IIntValueModel;
import net.sf.anathema.character.equipment.creation.presenter.RangedIntValueModel;

public class CloseCombatStatsticsModel extends OffensiveStatisticsModel implements ICloseCombatStatsticsModel {

  private final IIntValueModel defenseModel = new RangedIntValueModel(0);

  @Override
  public IIntValueModel getDefenseModel() {
    return defenseModel;
  }

}