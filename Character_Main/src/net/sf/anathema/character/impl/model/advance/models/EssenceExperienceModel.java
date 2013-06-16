package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.TraitCollection;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final TraitCollection traitCollection;
  private final IPointCostCalculator calculator;

  public EssenceExperienceModel(TraitCollection traitCollection, IPointCostCalculator calculator) {
    super("Experience", "Essence");
    this.traitCollection = traitCollection;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return calculator.getEssenceCosts(TraitCollectionUtilities.getEssence(traitCollection));
  }
}