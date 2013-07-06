package net.sf.anathema.character.model.creation.bonus;

import net.sf.anathema.character.model.creation.bonus.additional.MiscBonusModel;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.overview.IValueModel;

import java.util.ArrayList;
import java.util.List;

public class BonusPointCalculator {

  private final List<HeroBonusPointCalculator> allCalculators = new ArrayList<>();

  public void addBonusPointCalculator(HeroBonusPointCalculator additionalCalculator) {
    allCalculators.add(additionalCalculator);
  }

  public void recalculate() {
    for (HeroBonusPointCalculator calculator : allCalculators) {
      calculator.recalculate();
    }
  }

  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (HeroBonusPointCalculator calculator : allCalculators) {
      additionalGranted += calculator.getBonusPointsGranted();
    }
    return additionalGranted;
  }

  public IValueModel<Integer> getMiscellaneousModel() {
    return new MiscBonusModel(allCalculators);
  }
}