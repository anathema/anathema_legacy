package net.sf.anathema.character.model.advance.models;

import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.model.advance.IPointCostCalculator;

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