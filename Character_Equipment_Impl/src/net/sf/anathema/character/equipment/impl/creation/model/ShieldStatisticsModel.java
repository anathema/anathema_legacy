package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.IShieldStatisticsModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.SimpleIntValueModel;

public class ShieldStatisticsModel implements IShieldStatisticsModel {

  private final IIntValueModel closeCombatBonusModel = new SimpleIntValueModel(0);
  private final IIntValueModel rangedCombatBonusModel = new SimpleIntValueModel(0);

  public IIntValueModel getCloseCombatDvBonusModel() {
    return closeCombatBonusModel;
  }

  public IIntValueModel getRangedCombatDvBonusModel() {
    return rangedCombatBonusModel;
  }
}