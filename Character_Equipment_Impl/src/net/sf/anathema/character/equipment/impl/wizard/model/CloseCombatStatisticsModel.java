package net.sf.anathema.character.equipment.impl.wizard.model;

import net.disy.commons.core.model.IntModel;
import net.disy.commons.core.model.StringModel;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class CloseCombatStatisticsModel {

  private final IntModel speedModel = new IntModel();
  private final  StringModel nameModel = new StringModel();

  public boolean isComplete() {
    return true;
  }

  public IntModel getSpeedModel() {
    return speedModel;
  }

  public StringModel getNameModel() {
    return nameModel;
  }
}