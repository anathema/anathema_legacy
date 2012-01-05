package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.model.ICharacterStatistics;

public class MiscellaneousExperienceModel extends AbstractIntegerValueModel {
  private final ICharacterStatistics statistics;

  public MiscellaneousExperienceModel(ICharacterStatistics statistics) {
    super("Experience", "Miscellaneous"); //$NON-NLS-1$//$NON-NLS-2$
    this.statistics = statistics;
  }

  public Integer getValue() {
    return getMiscCosts();
  }

  private int getMiscCosts() {
    int total = 0;
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateCost();
    }
    total += statistics.getExperiencePoints().getExtraSpendings();
    return total;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
