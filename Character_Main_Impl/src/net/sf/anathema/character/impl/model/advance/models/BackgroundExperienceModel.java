package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class BackgroundExperienceModel implements IValueModel<Integer> {

  private final ICoreTraitConfiguration configuration;

  public BackgroundExperienceModel(ICoreTraitConfiguration configuration) {
    this.configuration = configuration;
  }

  public Integer getValue() {
    return getBackgroundExperience();
  }

  public String getId() {
    return "Backgrounds"; //$NON-NLS-1$
  }

  private int getBackgroundExperience() {
    int xpSum = 0;
    for (ITrait background : configuration.getBackgrounds().getBackgrounds()) {
      int difference = background.getCalculationValue() - background.getCreationValue();
      xpSum += difference * 3;
    }
    return xpSum;
  }
}