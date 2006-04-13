package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class WillpowerExperienceModel implements IValueModel<Integer> {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public WillpowerExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getWillpowerCosts();
  }

  public String getId() {
    return "Willpower"; //$NON-NLS-1$
  }

  private int getWillpowerCosts() {
    return calculator.getWillpowerCosts(traitConfiguration.getTrait(OtherTraitType.Willpower));
  }

}