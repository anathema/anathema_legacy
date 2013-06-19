package net.sf.anathema.character.impl.model.creation.bonus.additional;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelBonusPointCalculator;
import net.sf.anathema.character.impl.model.advance.models.AbstractIntegerValueModel;

import java.util.List;

public class MiscBonusModel extends AbstractIntegerValueModel {
  private final List<HeroModelBonusPointCalculator> additionalCalculators;

  public MiscBonusModel(List<HeroModelBonusPointCalculator> additionalCalculators) {
    super("Bonus", "MiscPoints");
    this.additionalCalculators = additionalCalculators;
  }

  @Override
  public Integer getValue() {
    return getAdditionalModelTotalValue();
  }

  private int getAdditionalModelTotalValue() {
    int additionalSpent = 0;
    for (HeroModelBonusPointCalculator calculator : additionalCalculators) {
      additionalSpent += calculator.getBonusPointCost();
    }
    return additionalSpent;
  }
}