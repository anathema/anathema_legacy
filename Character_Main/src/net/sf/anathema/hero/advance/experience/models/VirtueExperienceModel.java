package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.hero.advance.experience.PointCostCalculator;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.hero.traits.TraitMap;

public class VirtueExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitConfiguration;
  private final PointCostCalculator calculator;

  public VirtueExperienceModel(TraitMap traitConfiguration, PointCostCalculator calculator) {
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