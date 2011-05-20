package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;

public class MutationsBonusPointCalculator implements IAdditionalModelBonusPointCalculator {

  private int cost;
  private int gain;
  private final IMutationsModel model;

  public MutationsBonusPointCalculator(IMutationsModel model) {
    this.model = model;
  }

  public void recalculate() {
    cost = 0;
    gain = 0;
    for (IQualitySelection<IMutation> mutation : model.getSelectedQualities())
    {
    	cost += mutation.getPointValue() > 0 ? mutation.getPointValue() : 0;
    	gain -= mutation.getPointValue() < 0 ? mutation.getPointValue() : 0;
    }
  }

  public int getBonusPointCost() {
    return cost;
  }

  public int getBonusPointsGranted() {
    return gain;
  }
}