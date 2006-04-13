package net.sf.anathema.character.impl.model.creation.bonus.additional;

import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class MiscBonusModel implements IValueModel<Integer> {
  private final List<IAdditionalModelBonusPointCalculator> additionalCalculators;

  public MiscBonusModel(List<IAdditionalModelBonusPointCalculator> additionalCalculators) {
    this.additionalCalculators = additionalCalculators;
  }

  public Integer getValue() {
    return getAdditionalModelTotalValue();
  }

  public String getId() {
    return "Miscellaneous"; //$NON-NLS-1$
  }

  private int getAdditionalModelTotalValue() {
    int additionalSpent = 0;
    for (IAdditionalModelBonusPointCalculator calculator : additionalCalculators) {
      additionalSpent += calculator.getBonusPointCost();
    }
    return additionalSpent;
  }
}