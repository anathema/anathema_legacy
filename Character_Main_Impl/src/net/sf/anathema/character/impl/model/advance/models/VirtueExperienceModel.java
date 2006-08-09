package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.TraitCollectionUtitlies;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
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
    for (IDefaultTrait virtue : TraitCollectionUtitlies.getVirtues(traitConfiguration)) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }
}