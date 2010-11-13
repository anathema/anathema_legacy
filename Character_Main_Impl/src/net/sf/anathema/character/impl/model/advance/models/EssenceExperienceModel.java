package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final ITraitCollection traitCollection;
  private final IPointCostCalculator calculator;

  public EssenceExperienceModel(ITraitCollection traitCollection, IPointCostCalculator calculator) {
    super("Experience", "Essence"); //$NON-NLS-1$//$NON-NLS-2$
    this.traitCollection = traitCollection;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return calculator.getEssenceCosts(TraitCollectionUtilities.getEssence(traitCollection));
  }
}