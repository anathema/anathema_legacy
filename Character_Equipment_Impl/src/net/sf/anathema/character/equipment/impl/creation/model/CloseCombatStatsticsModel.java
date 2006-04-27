package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.ICloseCombatStatsticsModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.SimpleIntValueModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CloseCombatStatsticsModel implements ICloseCombatStatsticsModel {

  private final ITextualDescription name = new SimpleTextualDescription();
  private final IIntValueModel speedModel = new SimpleIntValueModel(1);
  private final IIntValueModel accuracyModel = new SimpleIntValueModel(0);
  private final IIntValueModel defenseModel = new SimpleIntValueModel(0);
  private final IIntValueModel rateModel = new SimpleIntValueModel(1);

  public ITextualDescription getName() {
    return name;
  }

  public IIntValueModel getSpeedModel() {
    return speedModel;
  }

  public IIntValueModel getAccuracyModel() {
    return accuracyModel;
  }

  public IIntValueModel getRateModel() {
    return rateModel;
  }

  public IIntValueModel getDefenseModel() {
    return defenseModel;
  }
}