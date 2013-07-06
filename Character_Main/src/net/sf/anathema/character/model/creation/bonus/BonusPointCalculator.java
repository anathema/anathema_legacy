package net.sf.anathema.character.model.creation.bonus;

import net.sf.anathema.hero.points.HeroModelBonusPointCalculator;
import net.sf.anathema.character.model.creation.bonus.additional.MiscBonusModel;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.IValueModel;

import java.util.ArrayList;
import java.util.List;

public class BonusPointCalculator {

  private final List<HeroModelBonusPointCalculator> allCalculators = new ArrayList<>();

  public void addBonusPointCalculator(HeroModelBonusPointCalculator additionalCalculator) {
    allCalculators.add(additionalCalculator);
  }

  public void recalculate() {
    for (HeroModelBonusPointCalculator calculator : allCalculators) {
      calculator.recalculate();
    }
  }

  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (HeroModelBonusPointCalculator calculator : allCalculators) {
      additionalGranted += calculator.getBonusPointsGranted();
    }
    return additionalGranted;
  }

  public void addMiscModel(List<IOverviewModel> models) {
    if (allCalculators.size() > 0) {
      models.add(getMiscellaneousModel());
    }
  }

  public IValueModel<Integer> getMiscellaneousModel() {
    return new MiscBonusModel(allCalculators);
  }
}