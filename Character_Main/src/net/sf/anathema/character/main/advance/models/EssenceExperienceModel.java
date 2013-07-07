package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.character.main.advance.IPointCostCalculator;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final TraitMap traitCollection;
  private final IPointCostCalculator calculator;

  public EssenceExperienceModel(TraitMap traitCollection, IPointCostCalculator calculator) {
    super("Experience", "Essence");
    this.traitCollection = traitCollection;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return calculator.getEssenceCosts(TraitCollectionUtilities.getEssence(traitCollection));
  }
}