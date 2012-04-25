package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;

public class IntimaciesBonusPointCalculator implements IAdditionalModelBonusPointCalculator {

  private final IIntimaciesModel model;
  private int cost;

  public IntimaciesBonusPointCalculator(IIntimaciesModel model) {
    this.model = model;
  }

  @Override
  public void recalculate() {
    this.cost = model.getEntries().size() > model.getFreeIntimacies() ? 3 : 0;
  }

  @Override
  public int getBonusPointCost() {
    return cost;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }
}