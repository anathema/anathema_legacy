package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.main.traits.model.TraitMap;

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