package net.sf.anathema.character.impl.model.creation.bonus.additional;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.impl.model.advance.models.AbstractIntegerValueModel;

import java.util.List;

public class MiscBonusModel extends AbstractIntegerValueModel {
  private final List<IAdditionalModelBonusPointCalculator> additionalCalculators;

  public MiscBonusModel(List<IAdditionalModelBonusPointCalculator> additionalCalculators) {
    super("Bonus", "MiscPoints");
    this.additionalCalculators = additionalCalculators;
  }

  @Override
  public Integer getValue() {
    return getAdditionalModelTotalValue();
  }

  private int getAdditionalModelTotalValue() {
    int additionalSpent = 0;
    for (IAdditionalModelBonusPointCalculator calculator : additionalCalculators) {
      additionalSpent += calculator.getBonusPointCost();
    }
    return additionalSpent;
  }
}