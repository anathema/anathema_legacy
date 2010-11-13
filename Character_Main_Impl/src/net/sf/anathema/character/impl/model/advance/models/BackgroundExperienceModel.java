package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class BackgroundExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration configuration;
  private final IExperiencePointCosts experiencePointCosts;

  public BackgroundExperienceModel(ICoreTraitConfiguration configuration, IExperiencePointCosts experiencePointCosts) {
    super("Experience", "Backgrounds"); //$NON-NLS-1$ //$NON-NLS-2$
    this.configuration = configuration;
    this.experiencePointCosts = experiencePointCosts;
  }

  public Integer getValue() {
    return getBackgroundExperience();
  }

  private int getBackgroundExperience() {
    int xpSum = 0;
    for (IDefaultTrait background : configuration.getBackgrounds().getBackgrounds()) {
      int difference = background.getCalculationValue() - background.getCreationValue();
      xpSum += difference * experiencePointCosts.getBackgroundCost();
    }
    return xpSum;
  }
}