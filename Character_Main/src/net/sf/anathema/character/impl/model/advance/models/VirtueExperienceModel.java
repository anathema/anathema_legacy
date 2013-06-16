package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitCollection;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;

public class VirtueExperienceModel extends AbstractIntegerValueModel {

  private final TraitCollection traitConfiguration;
  private final IPointCostCalculator calculator;

  public VirtueExperienceModel(TraitCollection traitConfiguration, IPointCostCalculator calculator) {
    super("Experience", "Virtues");
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getVirtueCosts();
  }

  private int getVirtueCosts() {
    int experienceCosts = 0;
    for (Trait virtue : TraitCollectionUtilities.getVirtues(traitConfiguration)) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }
}