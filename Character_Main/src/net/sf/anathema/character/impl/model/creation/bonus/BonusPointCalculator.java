package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelBonusPointCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.additional.MiscBonusModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.IValueModel;

import java.util.ArrayList;
import java.util.List;

public class BonusPointCalculator {

  private final List<HeroModelBonusPointCalculator> allAdditionalCalculators = new ArrayList<>();

  public void addAdditionalBonusPointCalculator(HeroModelBonusPointCalculator additionalCalculator) {
    allAdditionalCalculators.add(additionalCalculator);
  }

  public void recalculate() {
    for (HeroModelBonusPointCalculator calculator : allAdditionalCalculators) {
      calculator.recalculate();
    }
  }

  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (HeroModelBonusPointCalculator calculator : allAdditionalCalculators) {
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