package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.model.ICharacter;

public class MiscellaneousExperienceModel extends AbstractIntegerValueModel {
  private final ICharacter character;

  public MiscellaneousExperienceModel(ICharacter character) {
    super("Experience", "Miscellaneous");
    this.character = character;
  }

  @Override
  public Integer getValue() {
    return getMiscCosts();
  }

  private int getMiscCosts() {
    int total = 0;
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateCost();
    }
    total += character.getExperienceModel().getExperiencePoints().getExtraSpendings();
    return total;
  }
}
