package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class MiscellaneousExperienceModel implements IValueModel<Integer> {
  private final ICharacterStatistics statistics;

  public MiscellaneousExperienceModel(ICharacterStatistics statistics) {
    this.statistics = statistics;
  }

  public Integer getValue() {
    return getMiscCosts();
  }

  public String getId() {
    return "Miscellaneous"; //$NON-NLS-1$
  }

  private int getMiscCosts() {
    int total = 0;
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateCost();
    }
    return total;
  }
}
