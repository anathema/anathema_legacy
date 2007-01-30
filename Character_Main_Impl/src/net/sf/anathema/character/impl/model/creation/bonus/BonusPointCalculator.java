package net.sf.anathema.character.impl.model.creation.bonus;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.additional.MiscBonusModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class BonusPointCalculator {

  private final List<IAdditionalModelBonusPointCalculator> allAdditionalCalculators = new ArrayList<IAdditionalModelBonusPointCalculator>();

  public void addAdditionalBonusPointCalculator(IAdditionalModelBonusPointCalculator additionalCalculator) {
    allAdditionalCalculators.add(additionalCalculator);
  }

  public void recalculate() {
    for (IAdditionalModelBonusPointCalculator calculator : allAdditionalCalculators) {
      calculator.recalculate();
    }
  }

  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (IAdditionalModelBonusPointCalculator calculator : allAdditionalCalculators) {
      additionalGranted += calculator.getBonusPointsGranted();
    }
    return additionalGranted;
  }

  public void addMiscModel(List<IOverviewModel> models) {
    if (allAdditionalCalculators.size() > 0) {
      models.add(getAdditionalModelModel());
    }
  }

  public IValueModel<Integer> getAdditionalModelModel() {
    return new MiscBonusModel(allAdditionalCalculators);
  }
}