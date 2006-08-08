package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class VirtueExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public VirtueExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    super("Experience", "Virtues"); //$NON-NLS-1$ //$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getVirtueCosts();
  }

  private int getVirtueCosts() {
    int experienceCosts = 0;
    for (IModifiableTrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }
}