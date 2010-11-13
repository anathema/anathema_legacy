package net.sf.anathema.character.meritsflaws.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;

public class MeritsFlawsBonusPointCalculator implements IAdditionalModelBonusPointCalculator {

  private int cost;
  private int grant;
  private final IMeritsFlawsModel model;

  public MeritsFlawsBonusPointCalculator(IMeritsFlawsModel model) {
    this.model = model;
  }

  public void recalculate() {
    cost = 0;
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedMerits()) {
      if (selection.isCreationActive()) {
        cost += selection.getPointValue();
      }
    }
    grant = 0;
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedFlaws()) {
      if (selection.isCreationActive()) {
        grant += selection.getPointValue();
      }
    }
  }

  public int getBonusPointCost() {
    return cost;
  }

  public int getBonusPointsGranted() {
    return Math.min(10, grant);
  }
}