package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class EssenceExperienceModel implements IValueModel<Integer> {
  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public EssenceExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return calculator.getEssenceCosts(traitConfiguration.getTrait(OtherTraitType.Essence));
  }

  public String getId() {
    return "Essence"; //$NON-NLS-1$
  }
}
