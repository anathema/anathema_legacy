package net.sf.anathema.character.model.creation.bonus.additional;

import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.character.model.advance.models.AbstractIntegerValueModel;

import java.util.List;

public class MiscBonusModel extends AbstractIntegerValueModel {
  private final List<HeroBonusPointCalculator> additionalCalculators;

  public MiscBonusModel(List<HeroBonusPointCalculator> additionalCalculators) {
    super("Bonus", "MiscPoints");
    this.additionalCalculators = additionalCalculators;
  }

  @Override
  public Integer getValue() {
    return getAdditionalModelTotalValue();
  }

  private int getAdditionalModelTotalValue() {
    int additionalSpent = 0;
    for (HeroBonusPointCalculator calculator : additionalCalculators) {
      additionalSpent += calculator.getBonusPointCost();
    }
    return additionalSpent;
  }
}