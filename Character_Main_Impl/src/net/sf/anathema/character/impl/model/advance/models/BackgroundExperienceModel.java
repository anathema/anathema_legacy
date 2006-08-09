package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class BackgroundExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration configuration;

  public BackgroundExperienceModel(ICoreTraitConfiguration configuration) {
    super("Experience", "Backgrounds"); //$NON-NLS-1$ //$NON-NLS-2$
    this.configuration = configuration;
  }

  public Integer getValue() {
    return getBackgroundExperience();
  }

  private int getBackgroundExperience() {
    int xpSum = 0;
    for (IDefaultTrait background : configuration.getBackgrounds().getBackgrounds()) {
      int difference = background.getCalculationValue() - background.getCreationValue();
      xpSum += difference * 3;
    }
    return xpSum;
  }
}