package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public EssenceExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    super("Experience", "Essence"); //$NON-NLS-1$//$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return calculator.getEssenceCosts(traitConfiguration.getTrait(OtherTraitType.Essence));
  }
}