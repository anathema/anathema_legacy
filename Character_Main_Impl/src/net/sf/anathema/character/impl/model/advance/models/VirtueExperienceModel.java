package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class VirtueExperienceModel implements IValueModel<Integer> {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public VirtueExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getVirtueCosts();
  }

  public String getId() {
    return "Virtues"; //$NON-NLS-1$
  }

  private int getVirtueCosts() {
    int experienceCosts = 0;
    for (ITrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }
}