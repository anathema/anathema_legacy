package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.hero.advance.AbstractIntegerValueModel;
import net.sf.anathema.hero.advance.experience.PointCostCalculator;
import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.hero.traits.TraitMap;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final TraitMap traitCollection;
  private final PointCostCalculator calculator;

  public EssenceExperienceModel(TraitMap traitCollection, PointCostCalculator calculator) {
    super("Experience", "Essence");
    this.traitCollection = traitCollection;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return calculator.getEssenceCosts(TraitCollectionUtilities.getEssence(traitCollection));
  }
}